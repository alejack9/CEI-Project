package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;

/**
 * Represents boolean expressions that require all type of input (except VOID)
 */
public abstract class SPExpBinBoolAllIn extends SPExpBin {
	
	protected SPExpBinBoolAllIn(SPExp left, SPExp right) {
		super(left, right);
	}

	protected abstract String getOp();
	
	@Override
	public final Type inferType() throws TypeError {
		Type leftSideT = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();
		if(!leftSideT.equals(rightSideT))
			throw new TypeError("In operation \"" + this.getOp() + "\", left expression's type (" + leftSideT + ") does not equal to the right's type (" + rightSideT + ")");

		return EType.BOOL.getType();
	}
}
