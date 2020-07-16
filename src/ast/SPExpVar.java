package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import util_analysis.Environment;
import util_analysis.EnvironmentFun;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.GlobalVariableAccessError;
import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;

public class SPExpVar extends SPExp {

	private String id;
	private STEntry idEntry;
	

	public SPExpVar(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		idEntry = e.getIDEntry(id);
		
		if(idEntry == null)
			if(e instanceof EnvironmentFun)
				toRet.add(new GlobalVariableAccessError(id, line, column));
			else
				toRet.add(new VariableNotExistsError(id, line, column));
		
		return toRet;
	}

	@Override
	public Type inferType() {		
		return this.idEntry.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		BTEntry current = e.getIDEntry(id);
		if(e.update(
				id,
				new BTEntry(BTHelper.seq(
						current.getEffect(),
						EEffect.RW)))
				.getEffect()
				.compareTo(EEffect.T) == 0)
			toRet.add(new DeletedVariableError(id, line, column));
		
		return toRet;
	}

}
