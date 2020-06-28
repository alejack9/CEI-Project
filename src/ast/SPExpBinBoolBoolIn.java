package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
/**
 * Represents boolean expressions that require only INT type of input
 */

public abstract class SPExpBinBoolBoolIn extends SPExpBin {
	
	protected SPExpBinBoolBoolIn(SPExp left, SPExp right) {
		super(left, right);
	}
	
	@Override
	public final Type inferType() throws TypeError {
		Type leftSideT = this.leftSide.inferType();
		if(!EType.BOOL.equalsTo(leftSideT))
			throw new TypeError("Left expression in condition \"" + this.getOp() + "\" must return \"bool\" but it returns \"" + leftSideT + "\"");
		Type rightSideT = this.rightSide.inferType();
		if(!EType.BOOL.equalsTo(rightSideT))
			throw new TypeError("Right expression in condition \"" + this.getOp() + "\" must return \"bool\" but it returns \"" + rightSideT + "\"");
		
		return EType.BOOL.getType();
	}
}
