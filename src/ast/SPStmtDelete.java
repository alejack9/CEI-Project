package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
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
	public List<SemanticError> checkSemantics(Environment e) {
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
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
//		int cost ;
		//if the variable exist this will have a cost of -1
//		if(e.containsVariable(id))
//			cost = -1;
//		else cost = 0 ;
		
		//put the variable in the current scope with the current value
		e.deleteVariable(id);
		
		return null;
//		return new BTAtom(cost);
	}

	@Override
	public Type inferType() {
		if(EType.FUNCTION.equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Cannot delete a function", line, column));
		return EType.VOID.getType();
	}

}
