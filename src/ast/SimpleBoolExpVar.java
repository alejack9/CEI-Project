package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

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
			toRet.add(new SemanticError(Strings.ErrorVariableDoesntExist));
		return toRet;
	}

	@Override
	public STEntry getType(Environment e) {
		throw new Error("Method not implemented");
	}

}
