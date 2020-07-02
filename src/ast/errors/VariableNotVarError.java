package ast.errors;

/**
 * Represents a semantic error, in particular the variable is not referenced
 */
public class VariableNotVarError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public VariableNotVarError(String localId, int line, int col) {
		super(localId,SemanticErrorType.VARIABLENOTVAR, line, col);
	}

	@Override
	public String toString() {
		return this.getPosition() + " - Variable is not referenced. Variable name: " + id;
	}

}
