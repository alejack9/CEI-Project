package ast.errors;

public class SemanticError extends Error {
	private static final long serialVersionUID = 1L;

	protected String id;
	protected Integer errorLine;
	protected Integer errorColumn;
	public final SemanticErrorType errorType;

	protected SemanticError(String id, SemanticErrorType errorType) {
		this.id = id;
		this.errorType = errorType;
	}

	protected SemanticError(String id, SemanticErrorType errorType, int errorLine, int errorColumn) {
		this(id, errorType);
		this.errorLine = errorLine;
		this.errorColumn = errorColumn;
	}

	protected String getPosition() {
		return (errorLine != null) ? "[" + errorLine + " , " + errorColumn + "]" : "";
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
