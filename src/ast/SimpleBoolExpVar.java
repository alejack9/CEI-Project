package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import util_analysis.Environment;

public class SimpleBoolExpVar extends SimpleBoolExp {

	private String id;

	public SimpleBoolExpVar(String id) {
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariable(id)) 
			toRet.add(new VariableNotExistsError(id));
		return toRet;
	}

	@Override
	public Descriptor getType(Environment e) {
		throw new Error("Method not implemented");
	}

}
