package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class CondLess extends Cond {
	
	Exp leftSide, rightSide;

	public CondLess(Exp leftSide, Exp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));
			
		return toRet;
	}
}
