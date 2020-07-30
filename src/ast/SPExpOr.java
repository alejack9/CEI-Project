package ast;

import support.CodeGenUtils;
import support.CustomStringBuilder;

public class SPExpOr extends SPExpBinBoolBoolIn {

	public SPExpOr(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}
	
	@Override
	protected String getOp() {
		return "||";
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		
		valutate(leftSide, nl, T, sb, prev);
		valutate(rightSide, nl, T, sb, prev);
		
		sb.newLine(prev, "li $a0 0");
		sb.newLine(prev, "b ", end);
		sb.newLine(prev, T, ":");
		sb.newLine(prev, "li $a0 0");
		sb.newLine(prev, end, ":");
	}

	private void valutate(SPExp side, int nl, String label, CustomStringBuilder sb, String prev) {
		sb.newLine(prev, "# SPExpOr");
		side._codeGen(nl, sb);
		sb.newLine(prev, "li $t1 1");
		sb.newLine(prev, "beq $a0 $t1 ", label);
	}

}
