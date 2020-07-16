package ast;

import util_analysis.Environment;

public class SPExpLessThanEq extends SPExpBinArithm {
	
	public SPExpLessThanEq(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "<=";
	}
}
