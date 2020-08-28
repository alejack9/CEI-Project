package ast.errors;

/**
 * Represents a type error.
 */
public class TypeError extends Error {
	private static final long serialVersionUID = 1L;
	
	private int line;
	private int column;

	public TypeError(String message, int line, int column) {
		super(message);
		this.line = line;
		this.column = column;
	}

	@Override
	public String toString() {
		return "[ " + line + " : " + column + " ] - " + this.getMessage();
	}
}
