package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpDiv extends SPExpBinArithm {

	public SPExpDiv(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	public int getValue(Environment e) {
		return leftSide.getValue(e) / rightSide.getValue(e);
	}
	
	@Override
	protected String getOp() {
		return "/";
	}
}
