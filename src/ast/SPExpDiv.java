package ast;

public class SPExpDiv extends SPExpBinArithm {

	public SPExpDiv(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}
	
	@Override
	protected String getOp() {
		return "/";
	}

	@Override
	public String cGenOperator() {
		return "div";
	}
}
