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
	
	@Override
	public final Type inferType() throws TypeError {
		Type leftSideT = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();
		
		if(!leftSideT.equals(rightSideT))
			throw new TypeError("In condition \"" + this.getOp() + "\", left expression's type (" + leftSideT + ") does not equal to the right's type (" + rightSideT + ")");

		if(EType.VOID.equalsTo(leftSideT))
			throw new TypeError("Expressions must not be \"void\" type in operation \"" + this.getOp() + "\"");
		
		return EType.BOOL.getType();
	}
}
