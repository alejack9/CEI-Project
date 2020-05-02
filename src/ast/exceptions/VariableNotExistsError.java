package ast.exceptions;

public class VariableNotExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotExistsError(String variabileId) {
		super(variabileId);
	}
	public VariableNotExistsError(String variabileId, int line, int col) {
		super(variabileId, line, col);
	}
	
	@Override
	public String toString() {
		return "Variable doesn't exists. Variable name: " + this.id + getPosition();
	}
}
