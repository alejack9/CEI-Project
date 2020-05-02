package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleReturnVar extends SimpleStmtReturnRule {
	String ID;

	public SimpleReturnVar(String ID) {
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		if(!e.containsVariable(ID))
			result.add(new VariableNotExistsError(ID));
		
		return result;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
