package ast.errors;

/**
 * Represents a behaviour error, in particular when there is inconsistent
 * aliasing in the function invocation.
 */
public class AliasingError extends BehaviourError {
	private static final long serialVersionUID = 1L;

	private String funId;

	public AliasingError(String id, String funId, int errorLine, int errorColumn) {
		super(id, errorLine, errorColumn);
		this.funId = funId;
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Inconsistent behaviour for variable ").append(id)
				.append(" in ").append(funId).toString();
	}

}
