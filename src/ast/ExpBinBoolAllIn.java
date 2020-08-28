package ast;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.TypeErrorsStorage;

/**
 * Represents boolean expressions that require all type of input (except VOID)
 */
public abstract class ExpBinBoolAllIn extends ExpBin {

	private Type leftType;

	protected ExpBinBoolAllIn(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	public final Type inferType() {
		leftType = this.leftSide.inferType();
		Type rightSideT = this.rightSide.inferType();

		if (!leftType.getType().equalsTo(rightSideT))
			TypeErrorsStorage.addError(new TypeError("In condition \"" + this.getOp() + "\", left expression's type ("
					+ leftType + ") does not equal to the right's type (" + rightSideT + ")", line, column));

		if (EType.VOID.equalsTo(leftType))
			TypeErrorsStorage.addError(new TypeError(
					"Expressions must not be \"void\" type in operation \"" + this.getOp() + "\"", line, column));

		return EType.BOOL.getType();
	}

	protected abstract String trueReturn();

	protected abstract String falseReturn();

	@Override
	public final void _codeGen(int nl, CustomStringBuilder sb) {
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		leftSide._codeGen(nl, sb);
		sb.newLine("push $a0 ", Integer.toString(leftType.getDimension()));
		rightSide._codeGen(nl, sb);
		sb.newLine("lw $t1 0($sp) ", Integer.toString(leftType.getDimension()));
		sb.newLine("pop ", Integer.toString(leftType.getDimension()));
		sb.newLine("beq $a0 $t1 ", T);
		sb.newLine("li $a0 ", falseReturn());
		sb.newLine("b ", end);
		sb.newLine(T, ":");
		sb.newLine("li $a0 ", trueReturn());
		sb.newLine(end, ":");
	}
}
