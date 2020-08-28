package ast.errors;

/**
 * Represents a behaviour error.
 */
public abstract class BehaviourError extends SemanticError {
	private static final long serialVersionUID = 1L;

	protected BehaviourError(String id, int errorLine, int errorColumn) {
		super(id, SemanticErrorType.BEHAVIOURERROR, errorLine, errorColumn);
	}

	@Override
	public abstract String toString();
}
