package ast.errors;

/**
 * Represents a semantic error.
 */
public abstract class SemanticError extends Error {
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected int errorLine;
	protected int errorColumn;
	public final SemanticErrorType errorType;

	protected SemanticError(String id, SemanticErrorType errorType, int errorLine, int errorColumn) {
		this.id = id;
		this.errorType = errorType;
		this.errorLine = errorLine;
		this.errorColumn = errorColumn;
	}

	/**
	 * Gets the position.
	 *
	 * @return string - a string with the line and the column where there is an error
	 */
	protected String getPosition() {
		// asserting that, if "errorLine" is set, then "errorColumn" is set
		return "[ " + errorLine + " : " + errorColumn + " ]";
	}

	public SemanticErrorType getType() {
		return this.errorType;
	}

	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public abstract String toString();
}
