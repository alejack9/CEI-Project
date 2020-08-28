package ast.errors;

/**
 * Represents a semantic error, in particular the called function does not
 * exists
 */
public class DifferentVarTypeError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public DifferentVarTypeError(String variableId, int line, int col) {
		super(variableId, SemanticErrorType.DIFFERENTVARTYPE, line, col);
	}

	@Override
	public String toString() {
		return getPosition() + " - Type of variable \"" + this.id + "\" must be the same of the deleted one";
	}

}
