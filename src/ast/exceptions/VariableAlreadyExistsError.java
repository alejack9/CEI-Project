package ast.exceptions;

public class VariableAlreadyExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableAlreadyExistsError(String variabileId) {
		super(variabileId);
	}
	public VariableAlreadyExistsError(String variabileId, int line, int col) {
		super(variabileId, line, col);
	}
	
	@Override
	public String toString() {
		return "Variable already exists. Variable name: " + this.id + getPosition();
	}
}
