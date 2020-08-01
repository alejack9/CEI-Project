package ast;

public class ExpSum extends ExpBinArithm {
	
	public ExpSum(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "+";
	}

	@Override
	public String cGenOperator() {
		return "add";
	}
}
