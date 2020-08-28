/*
 * 
 */
package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpOr.
 */
public class ExpOr extends ExpBinBoolBoolIn {

	/**
	 * Instantiates a new exp or.
	 *
	 * @param left the left
	 * @param right the right
	 * @param line the line
	 * @param column the column
	 */
	public ExpOr(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Gets the op.
	 *
	 * @return the op
	 */
	@Override
	protected String getOp() {
		return "||";
	}

	/**
	 * Code gen.
	 *
	 * @param nl the nl
	 * @param sb the sb
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		valutate(leftSide, nl, T, sb);
		valutate(rightSide, nl, T, sb);

		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(T, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}

	/**
	 * Valutate.
	 *
	 * @param side the side
	 * @param nl the nl
	 * @param label the label
	 * @param sb the sb
	 */
	private void valutate(Exp side, int nl, String label, CustomStringBuilder sb) {
		side.codeGen(nl, sb);
		sb.newLine("li $t1 1");
		sb.newLine("beq $a0 $t1 ", label);
	}
}
