package ast.exceptions;

public class VariableNotVarError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotVarError(String localId) {
		super(localId);
	}

	public VariableNotVarError(String localId, int line, int col) {
		super(localId, line, col);
	}

	@Override
	public String toString() {
		return "Variable is not referenced. Variable name: " + this.id + getPosition();
	}

}
