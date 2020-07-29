package ast;

import java.util.LinkedList;
import java.util.List;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.LocalVariableDoesntExistsError;
import ast.errors.SemanticError;
import ast.errors.TypeError;
import ast.errors.VariableNotExistsError;
import ast.errors.VariableNotVarError;

public class SPStmtDelete extends SPStmt {

	private String id;
	private STEntry idEntry;

	/**
	 * Creates a delete statement
	 * @param id the variable we want to delete
	 */
	public SPStmtDelete(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}

	/*
	 * Checks if the variable in use exists. if it doesn't then add an error, 
	 * if it does then remove it from the current scope
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		STEntry candidate = e.getLocalIDEntry(id);
		if(candidate != null && !candidate.getType().IsParameter())
			idEntry = e.deleteVariable(id);
		else {
			candidate = e.getIDEntry(id);
			if(candidate == null)
				toRet.add(new VariableNotExistsError(id, line, column));
			else if(!candidate.getType().IsRef())
				if (candidate.getType().IsParameter())
					toRet.add(new VariableNotVarError(id, line, column));
				else 
					toRet.add(new LocalVariableDoesntExistsError(id, line, column));
			else
				idEntry = e.deleteVariable(id);
		}
		/**
		 * int yy;
		 * void f(var int x) {
		 * 	void z(var int y) {
		 * 		delete y;
		 * 	}
		 * 	z(x);
		 * }
		 * f(yy);
		 * yy = 3;
		 */
		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		e.getIDEntry(id).setLocalEffect(BTHelper.seq(e.getIDEntry(id).getLocalEffect(), EEffect.D));
		
		if(e.getIDEntry(id).getLocalEffect().equals(EEffect.T))
			toRet.add(new DeletedVariableError(id, line, column));
		
		return toRet;
	}

	@Override
	public Type inferType() {
		if(EType.FUNCTION.equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Cannot delete a function", line, column));
		return EType.VOID.getType();
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { }
}
