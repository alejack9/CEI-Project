package ast;

/**
 * The class of equal expressions ("x == y").
 */
public class ExpEqual extends ExpBinBoolAllIn {

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOperationSymbol() {
		return "==";
	}

	@Override
	protected String falseReturn() {
		return "0";
	}

	@Override
	protected String trueReturn() {
		return "1";
	}
}
