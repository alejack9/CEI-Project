package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Strings;
import util_analysis.Environment;
import util_analysis.SemanticError;

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
			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + id));
		
		return result;
	}

	@Override
	public int getValue(Environment e) {		
		return e.getVariableValue(id);
	}

}
