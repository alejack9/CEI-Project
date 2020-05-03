package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class BoolExpAnd extends BoolExp {
	BoolExp leftSide;
	BoolExp rightSide;

	public BoolExpAnd(BoolExp leftSide, BoolExp rightSide) {
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
