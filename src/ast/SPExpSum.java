package ast;

public class SPExpSum extends SPExpBinArithm {
	
	public SPExpSum(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "+";
	}

	@Override
	public String cGenOperator() {
		return "add";
	}
}
