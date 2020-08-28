/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpSum.
 */
public class ExpSum extends ExpBinArithm {

	/**
	 * Instantiates a new exp sum.
	 *
	 * @param leftSide the left side
	 * @param rightSide the right side
	 * @param line the line
	 * @param column the column
	 */
	public ExpSum(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "+";
	}

	/**
	 * C gen operator.
	 *
	 * @return the string
	 */
	@Override
	protected String cGenOperator() {
		return "add";
	}
}
