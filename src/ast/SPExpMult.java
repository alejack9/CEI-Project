package ast;

public class SPExpMult extends SPExpBinArithm {
	
	public SPExpMult(SPExp leftSide, SPExp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "*";
	}

	@Override
	public String cGenOperator() {
		return "mul";
	}
}
