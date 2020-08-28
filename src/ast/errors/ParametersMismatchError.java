package ast.errors;

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
		return this.getPosition() + " - Parameters required (" + params + ") doesn't match with passed params (" + exps
				+ ")";
	}
}
