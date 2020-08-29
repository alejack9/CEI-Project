package ast.errors;

/**
 * Represents a semantic error, in particular the required parameter is a
 * reference but a non-variable was passed.
 */
public class PassedExpNotVariableError extends SemanticError {
	private static final long serialVersionUID = 1L;

	private int parNumber;
	private String id;

	public PassedExpNotVariableError(int parNumber, String id, int line, int col) {
		super(null, SemanticErrorType.PASSEDREFERENCENOTVAR, line, col);
		this.parNumber = parNumber;
		this.id = id;
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - #").append(parNumber).append(" parameter of ").append(id)
				.append(" call must be a variable reference.").toString();
	}
}
