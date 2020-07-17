package ast;

public class SPExpDiff extends SPExpBinArithm {

	public SPExpDiff(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "-";
	}
}
