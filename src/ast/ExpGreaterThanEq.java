/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpGreaterThanEq.
 */
public class ExpGreaterThanEq extends ExpBinBoolIntIn {

	/**
	 * Instantiates a new exp greater than eq.
	 *
	 * @param leftSide the left side
	 * @param rightSide the right side
	 * @param line the line
	 * @param column the column
	 */
	public ExpGreaterThanEq(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return ">=";
	}

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	@Override
	protected String getOperator() {
		return "bge";
	}
}
