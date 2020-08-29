package ast.errors;

/**
 * Represents a semantic error, in particular the number of actual parameters is
 * not the same of formal parameters.
 */
public class ParametersMismatchError extends SemanticError {
	private static final long serialVersionUID = 1L;

	private int params;
	private int exps;

	public ParametersMismatchError(int params, int exps, int line, int col) {
		super(null, SemanticErrorType.PARAMETERSMISMATCH, line, col);
		this.params = params;
		this.exps = exps;
	}

	@Override
	public String toString() {
		return new StringBuilder(getPosition()).append(" - Parameters required (").append(params)
				.append(") doesn't match with passed params (").append(exps).append(")").toString();
	}
}
