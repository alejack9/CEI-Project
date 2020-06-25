package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;

public abstract class SPExpArithm extends SPExpBin {
	
	protected SPExpArithm(SPExp left, SPExp right) {
		super(left, right);
	}

	protected abstract String getOp();
	
	@Override
	public final Type inferType() throws TypeError {
		Type leftSideT = this.leftSide.inferType();
		if(!EType.INT.equalsTo(leftSideT))
			throw new TypeError("Left expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + leftSideT + "\" instead");
		Type rightSideT = this.rightSide.inferType();
		if(!EType.INT.equalsTo(rightSideT))
			throw new TypeError("Right expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + rightSideT + "\" instead");
		
		return EType.INT.getType();
	}
}
