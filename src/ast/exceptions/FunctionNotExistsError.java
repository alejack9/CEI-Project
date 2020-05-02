package ast.exceptions;

public class FunctionNotExistsError extends SemanticError {
	private static final long serialVersionUID = 1L;

	public FunctionNotExistsError(String functionId) {
		super(functionId);
	}

	public FunctionNotExistsError(String functionId, int line, int col) {
		super(functionId, line, col);
	}

	@Override
	public String toString() {
		return "No function found. Function doesn't exist. Function name: " + this.id + getPosition();
	}

}
