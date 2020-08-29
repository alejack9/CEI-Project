package ast.errors;

/**
 * Represents a semantic error, in particular the type of a declared variable is
 * not the same of the original variable (after delete).
 */
public class DifferentVarTypeError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public DifferentVarTypeError(String variableId, int line, int col) {
		super(variableId, SemanticErrorType.DIFFERENTVARTYPE, line, col);
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Type of variable \"").append(id)
				.append("\" must be the same of the deleted one").toString();
	}

}
