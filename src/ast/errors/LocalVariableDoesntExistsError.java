package ast.errors;

public class LocalVariableDoesntExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public LocalVariableDoesntExistsError(String localId) {
		super(localId, SemanticErrorType.LOCALVARIABLEDOESNTEXIST);
	}

	public LocalVariableDoesntExistsError(String localId, int line, int col) {
		super(localId, SemanticErrorType.LOCALVARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return "Variable doesn't exist in local scope. Variable name: " + this.id + getPosition();
	}

}
