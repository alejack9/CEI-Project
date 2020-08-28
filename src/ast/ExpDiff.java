/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpDiff.
 */
public class ExpDiff extends ExpBinArithm {

	/**
	 * Instantiates a new exp diff.
	 *
	 * @param leftSide the left side
	 * @param rightSide the right side
	 * @param line the line
	 * @param column the column
	 */
	public ExpDiff(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "-";
	}

	/**
	 * C gen operator.
	 *
	 * @return the string
	 */
	@Override
	protected String cGenOperator() {
		return "sub";
	}
}
