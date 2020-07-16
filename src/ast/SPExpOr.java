package ast;

import util_analysis.Environment;

public class SPExpOr extends SPExpBinBoolBoolIn {

	public SPExpOr(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	protected String getOp() {
		return "||";
	}

}
