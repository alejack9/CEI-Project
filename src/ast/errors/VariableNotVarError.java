package ast.errors;

public class VariableNotVarError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotVarError(String localId) {
		super(localId, SemanticErrorType.VARIABLENOTVAR);
	}

	public VariableNotVarError(String localId, int line, int col) {
		super(localId,SemanticErrorType.VARIABLENOTVAR, line, col);
	}

	@Override
	public String toString() {
		return "Variable is not referenced. Variable name: " + this.id + getPosition();
	}

}
