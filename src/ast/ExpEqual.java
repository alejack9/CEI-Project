/*
 * 
 */
package ast;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpEqual.
 */
public class ExpEqual extends ExpBinBoolAllIn {

	/**
	 * Instantiates a new exp equal.
	 *
	 * @param left the left
	 * @param right the right
	 * @param line the line
	 * @param column the column
	 */
	public ExpEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "==";
	}

	/**
	 * False return.
	 *
	 * @return the string
	 */
	@Override
	protected String falseReturn() {
		return "0";
	}

	/**
	 * True return.
	 *
	 * @return the string
	 */
	@Override
	protected String trueReturn() {
		return "1";
	}
}
