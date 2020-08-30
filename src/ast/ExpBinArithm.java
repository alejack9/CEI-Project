package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.TypeErrorsStorage;

/**
 * The base class of arithmetic expressions between two integers.
 */
public abstract class ExpBinArithm extends ExpBin {

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line of the code
	 * @param column the column of the code
	 */
	protected ExpBinArithm(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Check that left and right sides of the expression are {@link EType#INT INT}.
	 * 
	 * @return {@link EType#INT INT} as type
	 */
	@Override
	public final Type inferType() {
		Type leftSideT = leftSide.inferType();
		if (!EType.INT.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in operation \"" + this.getOperationSymbol()
					+ "\" must return int. It returns \"" + leftSideT + "\" instead", leftSide.line, leftSide.column));
		Type rightSideT = rightSide.inferType();
		if (!EType.INT.equalsTo(rightSideT))
			TypeErrorsStorage
					.addError(new TypeError(
							"Right expression in operation \"" + this.getOperationSymbol()
									+ "\" must return int. It returns \"" + rightSideT + "\" instead",
							rightSide.line, rightSide.column));

		return EType.INT.getType();
	}

	@Override
	public final void codeGen(int nl, CustomStringBuilder sb) {
		leftSide.codeGen(nl, sb);
		sb.newLine("push $a0 4");
		rightSide.codeGen(nl, sb);
		sb.newLine("lw $t1 0($sp) 4");
		sb.newLine(cGenOperator(), " $a0 $t1 $a0");
		sb.newLine("pop 4");
	}

	/**
	 * Get the related code of the specific arithmetic operation
	 *
	 * @return the related string
	 */
	protected abstract String cGenOperator();
}
