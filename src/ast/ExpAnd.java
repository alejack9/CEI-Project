package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

public class ExpAnd extends ExpBinBoolBoolIn {

	public ExpAnd(Exp left, Exp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "&&";
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		String F = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		valutate(leftSide, nl, F, sb);
		valutate(rightSide, nl, F, sb);
		sb.newLine("li $a0 1");
		sb.newLine("b ", end);
		sb.newLine(F, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}
	
	private void valutate(Exp side, int nl, String label, CustomStringBuilder sb) {
		side._codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", label);
	}
}
