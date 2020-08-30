package support;

import util_analysis.entries.STEntry;

public class CodeGenUtils {

	private static int id = 0;

	private CodeGenUtils() {
	}

	/**
	 * Load in the register "$al" the access link that points to the activation
	 * record in which is stored the variable.
	 * 
	 * @param variable the variable to access
	 * @param nl       the current nesting level
	 * @param sb       the string to add the assembly command
	 */
	private static void moveToCorrectAR(STEntry variable, int nl, CustomStringBuilder sb) {
		sb.newLine("move $al $fp");
		for (int i = 0; i < nl - variable.nestingLevel; i++)
			sb.newLine("lw $al 0($al) 4");
	}

	/**
	 * Load in "$a0" the passed variable value.
	 * 
	 * @param variable the variable to access
	 * @param nl       the current nesting level
	 * @param sb       the string to add the assembly command
	 */
	public static void getVariableCodeGen(STEntry variable, int nl, CustomStringBuilder sb) {
		moveToCorrectAR(variable, nl, sb);
		sb.newLine("lw $a0 ", Integer.toString(variable.offset), "($al) ",
				Integer.toString(variable.getType().getDimension()));
	}

	/**
	 * Load in the register "$a0" the address of the passed variable. <br>
	 * The address is obtained as the sum of the access link and the variable's
	 * offset.
	 * 
	 * @param variable the variable to access
	 * @param nl       the current nesting level
	 * @param sb       the string to add the assembly command
	 */
	public static void getVariableRefCodeGen(STEntry variable, int nl, CustomStringBuilder sb) {
		moveToCorrectAR(variable, nl, sb);
		sb.newLine("addi $a0 $al ", Integer.toString(variable.offset));
	}

	/**
	 * Create label.
	 * 
	 * @return label the label
	 */
	public static String freshLabel() {
		return "label" + id++;
	}
}
