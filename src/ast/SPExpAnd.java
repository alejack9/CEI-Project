package ast;

import util_analysis.Environment;

public class SPExpAnd extends SPExpBinBoolBoolIn {

	public SPExpAnd(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getOp() {
		return "&&";
	}
}
