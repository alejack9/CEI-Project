package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class ExpDiv extends Exp {
	
	Exp leftSide, rightSide;

	public ExpDiv(Exp leftSide, Exp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public Descriptor getType(Environment e) {
		return null;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		result.addAll(leftSide.checkSemantics(e));
		result.addAll(rightSide.checkSemantics(e));
		
		return result;
	}
}
