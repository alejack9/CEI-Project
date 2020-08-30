package ast.errors;

/**
 * Represents a behaviour error, in particular the variable has been deleted and
 * trying to use it.
 */
public class DeletedVariableError extends BehaviourError {
	private static final long serialVersionUID = 1L;

	public DeletedVariableError(String id, int errorLine, int errorColumn) {
		super(id, errorLine, errorColumn);
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Variable ").append(id).append(" has been deleted.")
				.toString();
	}
}
