package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleCondGreatEqual extends SimpleCond {
	
	SimpleExp leftSide, rightSide;

	public SimpleCondGreatEqual(SimpleExp leftSide, SimpleExp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public STEntry getType(Environment e) {
		return null;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));
			
		return toRet;
	}
}
