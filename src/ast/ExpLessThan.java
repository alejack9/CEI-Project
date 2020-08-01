package ast;

public class ExpLessThan extends ExpBinBoolIntIn {

	public ExpLessThan(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "<";
	}

	@Override
	protected String getOperator() {
		return "blt";
	}
}
