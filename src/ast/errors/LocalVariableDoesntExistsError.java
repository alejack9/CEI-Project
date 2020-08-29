package ast.errors;

/**
 * Represents a semantic error, in particular the used variable does not exist
 * in the local scope.
 */
public class LocalVariableDoesntExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public LocalVariableDoesntExistsError(String localId, int line, int col) {
		super(localId, SemanticErrorType.LOCALVARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return this.getPosition() + " - Variable \"" + id + "\" doesn't exist in local scope.";
	}

}
