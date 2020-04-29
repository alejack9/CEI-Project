package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleCondLess extends SimpleCond {
	
	SimpleExp leftSide, rightSide;

	public SimpleCondLess(SimpleExp leftSide, SimpleExp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public boolean getValue(Environment e) {
		return leftSide.getValue(e) < rightSide.getValue(e);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		throw new Error("Method not implemented");
//		List<SemanticError> result = new LinkedList<SemanticError>();
//		
//		result.addAll(leftSide.checkSemantics(e));
//		result.addAll(rightSide.checkSemantics(e));
//		
//		return result;
	}
}
