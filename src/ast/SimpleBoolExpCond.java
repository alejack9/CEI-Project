package ast;

import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;


public class SimpleBoolExpCond extends SimpleBoolExp {

	private SimpleCond value;

	public SimpleBoolExpCond(SimpleCond value) {
		this.value = value;
	}
	
	// No semantic errors here

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public boolean getValue(Environment e) {
		throw new Error("Method not implemented");
	}

}
