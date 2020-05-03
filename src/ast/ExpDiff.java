package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class ExpDiff extends Exp {

	Exp leftSide, rightSide;

	public ExpDiff(Exp leftSide, Exp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();

		result.addAll(leftSide.checkSemantics(e));
		result.addAll(rightSide.checkSemantics(e));

		return result;
	}

}
