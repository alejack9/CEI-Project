package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.TypeErrorsStorage;

/**
 * Represents boolean expressions that require all type of input (except VOID)
 */
public abstract class SPExpBinBoolAllIn extends SPExpBin {
	
	protected SPExpBinBoolAllIn(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public final Type inferType() {
		Type leftSideT = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();
		
		if(!leftSideT.getType().equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("In condition \"" + this.getOp() + "\", left expression's type (" + leftSideT + ") does not equal to the right's type (" + rightSideT + ")", line, column));

		if(EType.VOID.equalsTo(leftSideT))
			TypeErrorsStorage.addError(new TypeError("Expressions must not be \"void\" type in operation \"" + this.getOp() + "\"", line, column));
		
		return EType.BOOL.getType();
	}
}
