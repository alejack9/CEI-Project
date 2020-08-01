package ast;

public class ExpEqual extends ExpBinBoolAllIn {

	public ExpEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	protected String getOp() {
		return "==";
	}
	
	@Override
	protected String falseReturn() {
		return "0";
	}

	@Override
	protected String trueReturn() {
		return "1";
	}
}
