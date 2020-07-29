package support;

public class CustomStringBuilder {
	private StringBuilder sb;
	
	public CustomStringBuilder(StringBuilder sb) {
		this.sb = sb;
	}

	public void newLine(String... toAppend) {
		sb.append("\r\n");
		for(String s : toAppend)
			sb.append(s);
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
}
