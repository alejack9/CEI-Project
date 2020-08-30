package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

/**
 * The OR expression class.
 */
public class ExpOr extends ExpBinBoolBoolIn {

	/**
	 * @param exp    the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpOr(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOperationSymbol() {
		return "||";
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		String trueLabel = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		getCodeExpIsTrueThenJump(leftSide, nl, trueLabel, sb);
		getCodeExpIsTrueThenJump(rightSide, nl, trueLabel, sb);

		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(trueLabel, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}

	/**
	 * Return the code that evaluates the expression and, if the returned value is
	 * 1, make the program jump to a specified label.
	 */
	private void getCodeExpIsTrueThenJump(Exp side, int nl, String label, CustomStringBuilder sb) {
		side.codeGen(nl, sb);
		sb.newLine("li $t1 1");
		sb.newLine("beq $a0 $t1 ", label);
	}
}
