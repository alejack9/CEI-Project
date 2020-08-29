package ast.errors;

/**
 * Represents a behaviour error, in particular the declared id is already used.
 */
public class IdAlreadytExistsError extends BehaviourError {
	private static final long serialVersionUID = 1L;

	public IdAlreadytExistsError(String id, int line, int col) {
		super(id, line, col);
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Function or variable already exists with the same ID: ")
				.append(id).toString();
	}
}
