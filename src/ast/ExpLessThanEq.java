package ast;

public class ExpLessThanEq extends ExpBinBoolIntIn {
	
	public ExpLessThanEq(Exp left, Exp right, int line, int column) {
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
