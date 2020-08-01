package ast;

public class ExpGreaterThanEq extends ExpBinBoolIntIn {

	public ExpGreaterThanEq(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}
	
	@Override
	protected String getOp() {
		return ">=";
	}

	@Override
	protected String getOperator() {
		return "bge";
	}
}
