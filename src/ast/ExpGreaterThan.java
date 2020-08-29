package ast;

/**
 * The class of grater than expressions ("x > y").
 */
public class ExpGreaterThan extends ExpBinBoolIntIn {

	/**
	 * @param leftSide  the left side of the expression
	 * @param rightSide the right side of the expression
	 * @param line      the line in the code
	 * @param column    the column in the code
	 */
	public ExpGreaterThan(Exp leftSide, Exp rightSide, int line, int column) {
		super(leftSide, rightSide, line, column);
	}

	@Override
	protected String getOperationSymbol() {
		return ">";
	}

	@Override
	protected String getOperator() {
		return "bgt";
	}
}
