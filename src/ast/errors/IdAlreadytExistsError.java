package ast.errors;

/**
 * Represents a semantic error, in particular the declared id is already used
 */
public class IdAlreadytExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public IdAlreadytExistsError(String id) {
		super(id, SemanticErrorType.IDALREADYEXISTS);
	}

	public IdAlreadytExistsError(String id, int line, int col) {
		super(id,SemanticErrorType.IDALREADYEXISTS, line, col);
	}

	@Override
	public String toString() {
		return "Function or variable already exists with the same ID: " + this.id + getPosition();
	}
}
