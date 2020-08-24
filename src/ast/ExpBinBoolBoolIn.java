package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.TypeErrorsStorage;

/**
 * Represents boolean expressions that require only INT type of input
 */
public abstract class ExpBinBoolBoolIn extends ExpBin {
	
	protected ExpBinBoolBoolIn(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public final Type inferType() {
		Type leftSideT = this.leftSide.inferType();
		if(!EType.BOOL.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Left expression in condition \"" + this.getOp() + "\" must return \"bool\" but it returns \"" + leftSideT + "\"", leftSide.line, leftSide.column));
		Type rightSideT = this.rightSide.inferType();
		if(!EType.BOOL.equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("Right expression in condition \"" + this.getOp() + "\" must return \"bool\" but it returns \"" + rightSideT + "\"", rightSide.line, rightSide.column));
		
		return EType.BOOL.getType();
	}
}
