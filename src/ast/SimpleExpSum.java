package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.SemanticError;
import util_analysis.Environment;

public class SimpleExpSum extends SimpleExp {
	
	SimpleExp leftSide, rightSide;

	public SimpleExpSum(SimpleExp leftSide, SimpleExp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public STEntry getType(Environment e) {
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
