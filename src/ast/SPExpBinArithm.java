package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;

public abstract class SPExpBinArithm extends SPExpBin {
	
	protected SPExpBinArithm(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	public final Type inferType() throws TypeError {
		Type leftSideT = leftSide.inferType();
		if(!EType.INT.equalsTo(leftSideT))
			throw new TypeError("Left expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + leftSideT + "\" instead", line, column);
		Type rightSideT = rightSide.inferType();
		if(!EType.INT.equalsTo(rightSideT))
			throw new TypeError("Right expression in operation \"" + this.getOp() + "\" must return int. It returns \"" + rightSideT + "\" instead", line, column);
		
		return EType.INT.getType();
	}
}
