package ast;

public class ExpDiv extends ExpBinArithm {

	public ExpDiv(Exp leftSide, Exp rightSide, int line, int column) {
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
