/*
 * 
 */
package ast;

/**
 * The class of multiplication expressions.
 */
public class ExpMult extends ExpBinArithm {

	/**
	 * @param leftSide the left side of the expression
	 * @param rightSide the right side of the expression
	 * @param line the line in the code
	 * @param column the column in the code
	 */
	public ExpMult(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "*";
	}

	@Override
	protected String cGenOperator() {
		return "mul";
	}
}
