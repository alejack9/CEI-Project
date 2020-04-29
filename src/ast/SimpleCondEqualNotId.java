package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleCondEqualNotId extends SimpleCond {

	SimpleCondNot leftSide;
	String ID;

	public SimpleCondEqualNotId(SimpleCondNot leftSide, String id) {
		this.leftSide = leftSide;
		this.ID = id;
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
