package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import util_analysis.Environment;

public class SimpleExpVar extends SimpleExp {

	private String id;

	public SimpleExpVar(String id) {
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		if(!e.containsVariable(id))
			result.add(new VariableNotExistsError(id));
		
		return result;
	}

	@Override
	public Descriptor getType(Environment e) {
		return e.getVariableType(id);
	}

}
