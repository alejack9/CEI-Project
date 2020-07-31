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
	public void _codeGen(int nl, CustomStringBuilder sb) { 
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		
		valutate(leftSide, nl, T, sb);
		valutate(rightSide, nl, T, sb);
		
		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(T, ":");
		sb.newLine("li $a0 0");
		sb.newLine(end, ":");
	}

	private void valutate(SPExp side, int nl, String label, CustomStringBuilder sb) {
		side._codeGen(nl, sb);
		sb.newLine("li $t1 1");
		sb.newLine("beq $a0 $t1 ", label);
	}

}
