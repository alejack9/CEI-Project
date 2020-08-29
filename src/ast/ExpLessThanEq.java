/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpLessThanEq.
 */
public class ExpLessThanEq extends ExpBinBoolIntIn {

	/**
	 * Instantiates a new exp less than eq.
	 *
	 * @param left the left
	 * @param right the right
	 * @param line the line
	 * @param column the column
	 */
	public ExpLessThanEq(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "<=";
	}

	/**
	 * Gets the operator.
	 *
	 * @return the operator
	 */
	@Override
	protected String getOperator() {
		return "ble";
	}
}
