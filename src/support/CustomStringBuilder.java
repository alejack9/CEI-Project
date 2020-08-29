package support;

public class CustomStringBuilder {
	private StringBuilder sb;

	public CustomStringBuilder(StringBuilder sb) {
		this.sb = sb;
	}

	/**
	 * Write new line composed of the strings passed
	 * @param toAppend
	 */
	public void newLine(String... toAppend) {
		sb.append("\r\n");
		sameRow(toAppend);
	}
	
	// TODO
	/**
	 * 
	 * @param toAppend
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
