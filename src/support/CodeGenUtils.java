package support;

import util_analysis.entries.STEntry;

public class CodeGenUtils {
	private CodeGenUtils() {}
	
	public static String k;
	
	public static void getVariableCodeGen(STEntry variable, int nl, String finalOperation, CustomStringBuilder sb) {
		sb.newLine("lw $al 0($fp)");
		for(int i = 0; i < nl - variable.getNestingLevel(); i++)
			sb.newLine("\r\nlw $al 0($al)");
		sb.newLine(finalOperation, " $a0 ", variable.getOffset() + "($al)");
	}

	public static String freshLabel() {
		// TODO Auto-generated method stub
		throw new Error("Method not implemented");
	}
}
