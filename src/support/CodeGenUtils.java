package support;

import util_analysis.entries.STEntry;

public class CodeGenUtils {
	
	private static int id = 0;
	
	private CodeGenUtils() {}
	
	public static String k;
	
	public static void getVariableCodeGen(STEntry variable, int nl, String finalOperation, CustomStringBuilder sb) { 
		sb.newLine("lw $al 0($fp)");
		for(int i = 0; i < nl - variable.getNestingLevel(); i++)
			sb.newLine("lw $al 0($al)");
		sb.newLine(finalOperation, " $a0 ", Integer.toString(variable.getOffset()), "($al)");
	}

	public static String freshLabel() {
		return "label" + id++;
	}
}
