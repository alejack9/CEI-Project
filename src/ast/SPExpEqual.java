package ast;

public class SPExpEqual extends SPExpBinBoolAllIn {

	public SPExpEqual(SPExp left, SPExp right, int line, int column) {
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
