package ast.exceptions;

public class IdAlreadytExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public IdAlreadytExistsError(String id) {
		super(id);
	}

	public IdAlreadytExistsError(String id, int line, int col) {
		super(id, line, col);
	}

	@Override
	public String toString() {
		return "ID already exists. ID: " + this.id + getPosition();
	}
}
