package ast.errors;

public class PassedReferenceNotVarError extends SemanticError {
	private static final long serialVersionUID = 1L;
	private int parNumber;
	private String id;
	
	public PassedReferenceNotVarError(int parNumber, String id) {
		super(null, SemanticErrorType.PASSEDREFERENCENOTVAR);
		this.parNumber = parNumber;
		this.id = id;
	}

	public PassedReferenceNotVarError(int parNumber, String id, int line, int col) {
		super(null, SemanticErrorType.PASSEDREFERENCENOTVAR, line, col);
		this.parNumber = parNumber;
		this.id = id;
	}

	@Override
	public String toString() {
		return "#" + parNumber + " parameter of "+ id +" call must be a variable reference.";
	}
}
