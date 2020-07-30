package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

public class SPExpAnd extends SPExpBinBoolBoolIn {

	public SPExpAnd(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "&&";
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SPExpAnd");
		String F = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		valutate(leftSide, nl, F, sb, prev);
		valutate(rightSide, nl, F, sb, prev);
		sb.newLine(prev, "li $a0 1");
		sb.newLine(prev, "b ", end);
		sb.newLine(prev, F, ":");
		sb.newLine(prev, "li $a0 0");
		sb.newLine(prev, end, ":");
	}
	
	private void valutate(SPExp side, int nl, String label, CustomStringBuilder sb, String prev) {
		side._codeGen(nl, sb);
		sb.newLine(prev, "li $t1 0");
		sb.newLine(prev, "beq $a0 $t1 ", label);
	}
}
