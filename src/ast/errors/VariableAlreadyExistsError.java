package ast.errors;

public class VariableAlreadyExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableAlreadyExistsError(String variabileId) {
		super(variabileId, SemanticErrorType.VARIABLEALREADYEXISTS);
	}
	public VariableAlreadyExistsError(String variabileId, int line, int col) {
		super(variabileId, SemanticErrorType.VARIABLEALREADYEXISTS,line, col);
	}
	
	@Override
	public String toString() {
		return "Variable already exists. Variable name: " + this.id + getPosition();
	}
}
