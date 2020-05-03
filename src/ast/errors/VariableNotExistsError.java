package ast.errors;

public class VariableNotExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotExistsError(String variabileId) {
		super(variabileId, SemanticErrorType.VARIABLEDOESNTEXIST);
	}

	public VariableNotExistsError(String variabileId, int line, int col) {
		super(variabileId, SemanticErrorType.VARIABLEDOESNTEXIST, line, col);
	}

	@Override
	public String toString() {
		return "Variable doesn't exists. Variable name: " + this.id + getPosition();
	}
}
