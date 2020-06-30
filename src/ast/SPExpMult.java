package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class SPExpMult extends SPExpBinArithm {
	
	public SPExpMult(SPExp leftSide, SPExp rightSide) {
		super(leftSide, rightSide);
	}

	@Override
	public int getValue(Environment e) {
		return leftSide.getValue(e) * rightSide.getValue(e);
	}

	@Override
	protected String getOp() {
		return "*";
	}
}
