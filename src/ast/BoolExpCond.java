package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;


public class BoolExpCond extends BoolExp {

	private Cond value;

	public BoolExpCond(Cond value) {
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
