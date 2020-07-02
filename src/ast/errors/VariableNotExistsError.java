package ast.errors;

/**
 * Represents a semantic error, in particular the used variable does not exist
 */
public class VariableNotExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotExistsError(String variabileId, int line, int col) {
		super(variabileId, SemanticErrorType.VARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return this.getPosition() + " - Variable doesn't exists. Variable name: " + this.id;
	}
}
