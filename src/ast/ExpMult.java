package ast;

public class ExpMult extends ExpBinArithm {

	public ExpMult(Exp leftSide, Exp rightSide, int line, int column) {
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
