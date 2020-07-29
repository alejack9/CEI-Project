package ast;

public class SPExpLessThanEq extends SPExpBinBoolIntIn {
	
	public SPExpLessThanEq(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "<=";
	}


	@Override
	protected String getOperator() {
		return "ble";
	}
}
