/*
 * 
 */
package ast;

/**
 * The class of sum expressions.
 */
public class ExpSum extends ExpBinArithm {

	/**
	 * @param leftSide the left side of the expression
	 * @param rightSide the right side of the expression
	 * @param line the line in the code
	 * @param column the column in the code
	 */
	public ExpSum(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOp() {
		return "+";
	}

	@Override
	protected String cGenOperator() {
		return "add";
	}
}
