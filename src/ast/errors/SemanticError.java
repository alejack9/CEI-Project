package ast.errors;

/**
 * Represents a semantic error
 */
public class SemanticError extends Error {
	private static final long serialVersionUID = 1L;

	protected String id;
	protected Integer errorLine;
	protected Integer errorColumn;
	public final SemanticErrorType errorType;

	protected SemanticError(String id, SemanticErrorType errorType, Integer errorLine, Integer errorColumn) {
		this.id = id;
		this.errorType = errorType;
		this.errorLine = errorLine;
		this.errorColumn = errorColumn;
	}
	
	protected SemanticError(String id, SemanticErrorType errorType) {
		this(id, errorType, null, null);
	}

	protected String getPosition() {
		// asserting that, if "errorLine" is set, then "errorColumn" is set
		return (errorLine != null) ? "[" + errorLine + " : " + errorColumn + "]" : "";
	}

	public SemanticErrorType getType() {
		return this.errorType;
	}
	
	@Override
	public String getMessage() {
		return toString();
	}

	@Override
	public String toString() {
		return id;
	}
}
