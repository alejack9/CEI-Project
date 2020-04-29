package ast;

import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleBoolExpVar extends SimpleBoolExp {

	private String id;

	public SimpleBoolExpVar(String id) {
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public boolean getValue(Environment e) {
		throw new Error("Method not implemented");
	}

}
