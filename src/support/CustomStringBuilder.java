package support;

/**
 * Create string of assembly commands.
 */
public class CustomStringBuilder {
	private StringBuilder sb;

	public CustomStringBuilder(StringBuilder sb) {
		this.sb = sb;
	}

	/**
	 * Write new line composed of the strings passed
	 * 
	 * @param toAppend - list of string
	 */
	public void newLine(String... toAppend) {
		sb.append("\r\n");
		sameRow(toAppend);
	}

	/**
	 * Append to the current string the strings passed as parameters
	 * 
	 * @param toAppend - list of string
	 */
	public void sameRow(String... toAppend) {
		for (String s : toAppend)
			sb.append(s);
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}
