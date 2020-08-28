package ast;

public class ExpDiff extends ExpBinArithm {

	public ExpDiff(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "-";
	}

	@Override
	public String cGenOperator() {
		return "sub";
	}
}
