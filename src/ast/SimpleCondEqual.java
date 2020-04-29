package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleCondEqual extends SimpleCond {

	SimpleExp leftExpSide, rightExpSide;

	public SimpleCondEqual(SimpleExp leftSide, SimpleExp rightSide) {
		this.leftExpSide = leftSide;
		this.rightExpSide = rightSide;
	}
	
	@Override
	public boolean getValue(Environment e) {
		throw new Error("Method not implemented");
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
