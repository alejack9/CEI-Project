package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

/**
 * The AND expression class.
 */
public class ExpAnd extends ExpBinBoolBoolIn {

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpAnd(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOperationSymbol() {
		return "&&";
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		String falseLabel = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		getCodeExpIsFalseThenJump(leftSide, nl, falseLabel, sb);
		getCodeExpIsFalseThenJump(rightSide, nl, falseLabel, sb);

		sb.newLine("li $a0 1");
		sb.newLine("b ", end);
		sb.newLine(falseLabel, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}

	/**
	 * Return the code that evaluate the expression and, if the returned value is 0,
	 * make the program jump to a specified label.
	 */
	private void getCodeExpIsFalseThenJump(Exp side, int nl, String label, CustomStringBuilder sb) {
		side.codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", label);
	}
}
