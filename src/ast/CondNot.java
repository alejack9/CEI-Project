package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class CondNot extends Cond {

	private BoolExp exp;

	public CondNot(BoolExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}
}
