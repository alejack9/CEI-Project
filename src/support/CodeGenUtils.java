package support;

import util_analysis.entries.STEntry;

public class CodeGenUtils {

	private static int id = 0;

	private CodeGenUtils() {
	}

	public static String k;
	
	/**
	 * Access to a variable in the same or external activation record
	 * @param variable
	 * @param nl
	 * @param sb
	 */
	public static void getVariableCodeGen(STEntry variable, int nl, CustomStringBuilder sb) {
		sb.newLine("move $al $fp");
		for (int i = 0; i < nl - variable.nestingLevel; i++)
			sb.newLine("lw $al 0($al) 4");
		sb.newLine("lw $a0 ", Integer.toString(variable.offset), "($al) ",
				Integer.toString(variable.getType().getDimension()));
	}

	/**
	 * Create label
	 * @return label
	 */
	public static String freshLabel() {
		return "label" + id++;
	}
}
