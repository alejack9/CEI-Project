package ast;

import util_analysis.Environment;

public class SPExpSum extends SPExpBinArithm {
	
	public SPExpSum(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "+";
	}
}
