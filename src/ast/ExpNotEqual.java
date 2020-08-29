/*
 * 
 */
package ast;

/**
 * The class of not-equal expressions ("x != y").
 */
public class ExpNotEqual extends ExpBinBoolAllIn {

	/**
	 * @param leftSide the left side of the expression
	 * @param rightSide the right side of the expression
	 * @param line the line in the code
	 * @param column the column in the code
	 */
	public ExpNotEqual(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "!=";
	}

	@Override
	protected String trueReturn() {
		return "0";
	}

	@Override
	protected String falseReturn() {
		return "1";
	}
}
