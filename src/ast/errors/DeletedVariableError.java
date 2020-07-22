package ast.errors;

public class DeletedVariableError extends BehaviourError {
	private static final long serialVersionUID = 1L;

	public DeletedVariableError(String id, int errorLine, int errorColumn) {
		super(id, errorLine, errorColumn);
	}

	@Override
	public String toString() {
		return getPosition() + " - Variable " + id + " has been deleted.";
	}

}