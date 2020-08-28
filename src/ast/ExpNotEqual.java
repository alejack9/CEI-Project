package ast;

public class ExpNotEqual extends ExpBinBoolAllIn {

	public ExpNotEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "!=";
	}

	@Override
	protected String trueReturn() {
		return "0";
	}

	@Override
	protected String falseReturn() {
		return "1";
	}
}
