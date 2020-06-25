package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPExpDiv extends SPExp {
	
	SPExp leftSide, rightSide;

	public SPExpDiv(SPExp leftSide, SPExp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public int getValue(Environment e) {
		return leftSide.getValue(e) / rightSide.getValue(e);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		result.addAll(leftSide.checkSemantics(e));
		result.addAll(rightSide.checkSemantics(e));
		
		return result;
	}

	@Override
	public Type inferType() {
		// TODO Auto-generated method stub
		return null;
	}
}
