/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpNotEqual.
 */
public class ExpNotEqual extends ExpBinBoolAllIn {

	/**
	 * Instantiates a new exp not equal.
	 *
	 * @param left the left
	 * @param right the right
	 * @param line the line
	 * @param column the column
	 */
	public ExpNotEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "!=";
	}

	/**
	 * True return.
	 *
	 * @return the string
	 */
	@Override
	protected String trueReturn() {
		return "0";
	}

	/**
	 * False return.
	 *
	 * @return the string
	 */
	@Override
	protected String falseReturn() {
		return "1";
	}
}
