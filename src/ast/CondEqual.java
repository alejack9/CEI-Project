package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class CondEqual extends Cond {

	Exp leftExpSide, rightExpSide;

	public CondEqual(Exp leftSide, Exp rightSide) {
		this.leftExpSide = leftSide;
		this.rightExpSide = rightSide;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftExpSide.checkSemantics(e));
		toRet.addAll(rightExpSide.checkSemantics(e));
			
		return toRet;
	}
}
