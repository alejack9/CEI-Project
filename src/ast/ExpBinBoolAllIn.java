/*
 * 
 */
package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.TypeErrorsStorage;

/**
 * The base class of boolean expressions between all types.
 */
public abstract class ExpBinBoolAllIn extends ExpBin {

	/** The type of the left side */
	private Type leftType;

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line of the code
	 * @param column the column of the code
	 */
	protected ExpBinBoolAllIn(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Check that left and right sides of the expression are the same type (but not
	 * {@link EType#VOID VOID}).
	 * 
	 * @return {@link EType#BOOL BOOL} as type
	 */
	@Override
	public final Type inferType() {
		leftType = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();

		if (!leftType.getType().equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("In condition \"" + this.getOp() + "\", left expression's type ("
					+ leftType + ") does not equal to the right's type (" + rightSideT + ")", line, column));

		if (EType.VOID.equalsTo(leftType))
			TypeErrorsStorage.addError(new TypeError(
					"Expressions must not be \"void\" type in operation \"" + this.getOp() + "\"", line, column));

		return EType.BOOL.getType();
	}

	/**
	 * the true return to put in the generated code ("0" in "not equals", "1"
	 * otherwise).
	 *
	 * @return the string
	 */
	protected abstract String trueReturn();

	/**
	 * the false return to put in the generated code ("1" in "not equals", "0"
	 * otherwise).
	 *
	 * @return the string
	 */
	protected abstract String falseReturn();

	@Override
	protected final void codeGen(int nl, CustomStringBuilder sb) {
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		leftSide.codeGen(nl, sb);
		sb.newLine("push $a0 ", Integer.toString(leftType.getDimension()));
		rightSide.codeGen(nl, sb);
		sb.newLine("lw $t1 0($sp) ", Integer.toString(leftType.getDimension()));
		sb.newLine("pop ", Integer.toString(leftType.getDimension()));
		sb.newLine("beq $a0 $t1 ", T);
		sb.newLine("li $a0 ", falseReturn());
		sb.newLine("b ", end);
		sb.newLine(T, ":");
		sb.newLine("li $a0 ", trueReturn());
		sb.newLine(end, ":");
	}
}
