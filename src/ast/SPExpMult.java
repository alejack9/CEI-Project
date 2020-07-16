package ast;

import util_analysis.Environment;

public class SPExpMult extends SPExpBinArithm {
	
	public SPExpMult(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "*";
	}
}
