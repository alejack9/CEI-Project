package ast.errors;

/**
 * Represents a semantic error, in particular the used variable does not exist
 */
public class GlobalVariableAccessError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public GlobalVariableAccessError(String variabileId, int line, int col) {
		super(variabileId, SemanticErrorType.VARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return this.getPosition() + " - Unable to access to variable: " + this.id;
	}
}
