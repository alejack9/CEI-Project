package ast.errors;

/**
 * Represents a semantic error, in particular the variable that is required to
 * delete is already deleted.
 */
public class VariableAlreadyDeletedError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableAlreadyDeletedError(String variabileId, int line, int col) {
		super(variabileId, SemanticErrorType.VARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Variable was already deleted. Variable name: ").append(id)
				.toString();
	}
}
