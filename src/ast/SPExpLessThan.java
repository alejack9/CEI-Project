package ast;

public class SPExpLessThan extends SPExpBinBoolIntIn {

	public SPExpLessThan(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "<";
	}
}
