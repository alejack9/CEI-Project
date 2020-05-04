package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class CondEqualNot extends Cond {

	CondNot leftSide, rightSide;

	/**
	 * @param leftSide
	 * @param rightSide Can be null
	 */
	public CondEqualNot(CondNot leftSide, CondNot rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftSide.checkSemantics(e));
		//Check if the right side of the relationship operation is defined
		if(rightSide != null) toRet.addAll(rightSide.checkSemantics(e));
			
		return toRet;
	}
}
