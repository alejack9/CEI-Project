package ast;

/**
 * The class of less than or equal expressions ("x &lt;= y").
 */
public class ExpLessThanEq extends ExpBinBoolIntIn {

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpLessThanEq(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOperationSymbol() {
		return "<=";
	}

	@Override
	protected String getOperator() {
		return "ble";
	}
}
