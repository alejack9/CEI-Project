package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
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
	
	protected abstract String getOperator();
	
	@Override
	public final void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SPExpBinBoolIntIn ", getOperator());
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		leftSide._codeGen(nl, sb);
		sb.newLine(prev, "push $a0");
		rightSide._codeGen(nl, sb);
		sb.newLine(prev, "lw $t1 0($sp)");
		sb.newLine(prev, "pop");
		sb.newLine(prev, getOperator(), " $a0 $t1 ", T);
		sb.newLine(prev, "li $a0 0");
		sb.newLine(prev, "b ", end);
		sb.newLine(prev, T, ":");
		sb.newLine(prev, "li $a0 1");
		sb.newLine(prev, end, ":");
	}
}
