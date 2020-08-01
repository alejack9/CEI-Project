package ast;

public class ExpGreaterThan extends ExpBinBoolIntIn {

	public ExpGreaterThan(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}
	
	@Override
	protected String getOp() {
		return ">";
	}

	@Override
	protected String getOperator() {
		return "bgt";
	}
}
