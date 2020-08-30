package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.TypeErrorsStorage;

/**
 * The base class of boolean expressions between two integers.
 */
public abstract class ExpBinBoolIntIn extends ExpBin {

	/**
	 * @param left   the left
	 * @param right  the right
	 * @param line   the line
	 * @param column the column
	 */
	protected ExpBinBoolIntIn(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Check that left and right sides of the expression are {@link EType#INT INT}).
	 * 
	 * @return {@link EType#BOOL BOOL} as type
	 */
	@Override
	public final Type inferType() {
		Type leftSideT = this.leftSide.inferType();
		if (!EType.INT.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in condition \"" + this.getOperationSymbol()
					+ "\" must return int. It returns \"" + leftSideT + "\" instead", leftSide.line, leftSide.column));
		Type rightSideT = this.rightSide.inferType();
		if (!EType.INT.equalsTo(rightSideT))
			TypeErrorsStorage
					.addError(new TypeError(
							"Right expression in condition \"" + this.getOperationSymbol()
									+ "\" must return int. It returns \"" + rightSideT + "\" instead",
							rightSide.line, rightSide.column));

		return EType.BOOL.getType();
	}

	/**
	 * Get the related code of the specific arithmetic operation
	 *
	 * @return the related string
	 */
	protected abstract String getOperator();

	@Override
	public final void codeGen(int nl, CustomStringBuilder sb) {
		String trueLabel = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		leftSide.codeGen(nl, sb);
		sb.newLine("push $a0 4");
		rightSide.codeGen(nl, sb);
		sb.newLine("lw $t1 0($sp) 4");
		sb.newLine("pop 4");
		sb.newLine(getOperator(), " $t1 $a0 ", trueLabel);
		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(trueLabel, ":");
		sb.newLine("li $a0 1");
		sb.newLine(end, ":");
	}
}
