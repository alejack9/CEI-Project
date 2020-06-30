package ast;

import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpGreaterThanEq extends SPExpBinBoolIntIn {

	public SPExpGreaterThanEq(SPExp leftSide, SPExp rightSide) {
		super(leftSide, rightSide);
	}
	
	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getOp() {
		return ">=";
	}
}
