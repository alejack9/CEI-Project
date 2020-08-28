/*
 * 
 */
package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.TypeErrorsStorage;

/**
 * The base class of binary expressions between two booleans
 */
public abstract class ExpBinBoolBoolIn extends ExpBin {

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line of the code
	 * @param column the column of the code
	 */
	protected ExpBinBoolBoolIn(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	/**
	 * Check that left and right sides of the expression are booleans and returns a
	 * boolean type
	 *
	 * @return the type
	 */
	@Override
	public final Type inferType() {
		Type leftSideT = this.leftSide.inferType();
		if (!EType.BOOL.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in condition \"" + this.getOp()
					+ "\" must return \"bool\" but it returns \"" + leftSideT + "\"", leftSide.line, leftSide.column));
		Type rightSideT = this.rightSide.inferType();
		if (!EType.BOOL.equalsTo(rightSideT))
			TypeErrorsStorage
					.addError(new TypeError(
							"Right expression in condition \"" + this.getOp()
									+ "\" must return \"bool\" but it returns \"" + rightSideT + "\"",
							rightSide.line, rightSide.column));

		return EType.BOOL.getType();
	}
}
