package support;

import util_analysis.entries.STEntry;

public class CodeGenUtils {
	
	private static int id = 0;
	
	private CodeGenUtils() {}
	
	public static String k;
	
	public static void getVariableCodeGen(STEntry variable, int nl, CustomStringBuilder sb) { 
		sb.newLine("lw $al 0($fp) 4");
		for(int i = 0; i < nl - variable.getNestingLevel(); i++)
			sb.newLine("lw $al 0($al) 4");
		sb.newLine("lw $a0 ", Integer.toString(variable.getOffset()), "($al)", Integer.toString(variable.getType().getDimension()));
	}

	public static String freshLabel() {
		return "label" + id++;
	}
}
