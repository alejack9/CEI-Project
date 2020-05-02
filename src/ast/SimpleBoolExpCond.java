package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;


public class SimpleBoolExpCond extends SimpleBoolExp {

	private SimpleCond value;

	public SimpleBoolExpCond(SimpleCond value) {
		this.value = value;
	}
	
	// No semantic errors here

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return value.checkSemantics(e);
	}

	@Override
	public Descriptor getType(Environment e) {
		throw new Error("Method not implemented");
	}

}
