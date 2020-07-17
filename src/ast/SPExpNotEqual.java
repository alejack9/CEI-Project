package ast;

public class SPExpNotEqual extends SPExpBinBoolAllIn {

	public SPExpNotEqual(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	protected String getOp() {
		return "!=";
	}

}
