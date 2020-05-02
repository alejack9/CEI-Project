package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class SimpleCondEqualNot extends SimpleCond {

	SimpleCondNot leftSide, rightSide;

	public SimpleCondEqualNot(SimpleCondNot leftSide, SimpleCondNot rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}
	
	@Override
	public Descriptor getType(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));
			
		return toRet;
	}
}
