/*
 * 
 */
package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

/**
 * The AND expression class.
 */
public class ExpAnd extends ExpBinBoolBoolIn {

	/**
	 * @param left the left
	 * @param right the right
	 * @param line the line
	 * @param column the column
	 */
	public ExpAnd(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "&&";
	}

	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		String F = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		getCodeExpIsFalseThenJump(leftSide, nl, F, sb);
		getCodeExpIsFalseThenJump(rightSide, nl, F, sb);
		sb.newLine("li $a0 1");
		sb.newLine("b ", end);
		sb.newLine(F, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}

	private void getCodeExpIsFalseThenJump(Exp side, int nl, String label, CustomStringBuilder sb) {
		side.codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", label);
	}
}
