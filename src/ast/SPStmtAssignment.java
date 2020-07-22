package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.errors.VariableNotExistsError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.SemanticError;

public class SPStmtAssignment extends SPStmt{
	
	private String id;
	private STEntry idEntry;
	private SPExp exp;

	public SPStmtAssignment(String id, SPExp exp, int line, int column) {
		super(line, column);
		this.id = id;
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(id);
		
		if(idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));
				
		toRet.addAll(exp.checkSemantics(e));

		return toRet;		
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
	
		toRet.addAll(exp.inferBehaviour(e));
		
		e.update(id, new BTEntry(BTHelper.seq(e.getIDEntry(id).getEffect(), EEffect.RW)));
		
	    if(e.getIDEntry(id).getEffect().equals(EEffect.T))
	      toRet.add(new DeletedVariableError(id, line, column));
	    
	    return toRet;
	}

	@Override
	public Type inferType() {
		Type expType = exp.inferType(); 
		if(!expType.getType().equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Variable type (" + idEntry.getType() + ") is not equal to expression type (" + expType +")", line, column));
		
		return EType.VOID.getType();
	}
}
