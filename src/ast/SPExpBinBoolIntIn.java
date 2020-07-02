package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.TypeErrorsStorage;
/**
 * Represents boolean expressions that require only INT type of input
 */

public abstract class SPExpBinBoolIntIn extends SPExpBin {
	
	protected SPExpBinBoolIntIn(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public final Type inferType() {
		Type leftSideT = this.leftSide.inferType();
		if(!EType.INT.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in condition \"" + this.getOp() + "\" must return int. It returns \"" + leftSideT + "\" instead", leftSide.line, leftSide.column));
		Type rightSideT = this.rightSide.inferType();
		if(!EType.INT.equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("Right expression in condition \"" + this.getOp() + "\" must return int. It returns \"" + rightSideT + "\" instead", rightSide.line, rightSide.column));
		
		return EType.BOOL.getType();
	}
}
