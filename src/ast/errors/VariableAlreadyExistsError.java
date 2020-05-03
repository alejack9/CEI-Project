package ast.errors;
/**
 * 
 * Represents a semantic error, in particular the declared variable already exists in the local scope
 */
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
		return "Variable already exists in the local scope. Variable name: " + this.id + getPosition();
	}
}
