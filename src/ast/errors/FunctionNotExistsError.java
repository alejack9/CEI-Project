package ast.errors;

/**
 * Represents a semantic error, in particular the called function does not
 * exists.
 */
public class FunctionNotExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public FunctionNotExistsError(String functionId, int line, int col) {
		super(functionId, SemanticErrorType.FUNCTIONNOTEXISTS, line, col);
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - No function found. Function doesn't exist. Function name: ")
				.append(id).toString();
	}

}
