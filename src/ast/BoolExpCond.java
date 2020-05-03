package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;


public class BoolExpCond extends BoolExp {

	private Cond value;

	public BoolExpCond(Cond value) {
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return value.checkSemantics(e);
	}

}
