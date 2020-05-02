package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class SimpleCondEqual extends SimpleCond {

	SimpleExp leftExpSide, rightExpSide;

	public SimpleCondEqual(SimpleExp leftSide, SimpleExp rightSide) {
		this.leftExpSide = leftSide;
		this.rightExpSide = rightSide;
	}
	
	@Override
	public Descriptor getType(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftExpSide.checkSemantics(e));
		toRet.addAll(rightExpSide.checkSemantics(e));
			
		return toRet;
	}
}
