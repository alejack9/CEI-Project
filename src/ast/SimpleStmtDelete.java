package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.LocalVariableDoesntExistsError;
import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import ast.exceptions.VariableNotVarError;
import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleStmtDelete extends SimpleStmt {

	private String id;

	/**
	 * Creates a delete statement
	 * 
	 * @param id the variable we want to delete
	 */
	public SimpleStmtDelete(String id) {
		this.id = id;
	}

	/*
	 * Checks if the variable in use exists. if it doesn't then add an error, if it
	 * does then remove it from the current scope
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();

		if (e.containsVariable(id))
			if (e.containsVariableLocal(id))
				e.deleteVariable(id);
			else if (e.getVariableType(id) instanceof ParameterDescriptor)
				if (((ParameterDescriptor) e.getVariableType(id)).isVar())
					e.deleteVariable(id);
				else
					result.add(new VariableNotVarError(id));
			else
				result.add(new LocalVariableDoesntExistsError(id));
		else
			result.add(new VariableNotExistsError(id));
		
		return result;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		int cost;
		// if the variable exist this will have a cost of -1
		if (e.containsVariable(id))
			cost = -1;
		else
			cost = 0;

		// put the variable in the current scope with the current value
		e.deleteVariable(id);

		return new BTAtom(cost);
	}

}
