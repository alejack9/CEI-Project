package ast;

import util_analysis.Environment;

public class SPExpLessThan extends SPExpBinBoolIntIn {

	public SPExpLessThan(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getOp() {
		return "<";
	}
}
