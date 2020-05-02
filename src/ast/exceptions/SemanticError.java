package ast.exceptions;

public class SemanticError extends Error {
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected Integer errorLine;
	protected Integer errorColumn;
	
	protected SemanticError(String id){
		this.id = id;
	}
	
	protected SemanticError(String id, int errorLine, int errorColumn){
		this.id = id;
		this.errorLine = errorLine;
		this.errorColumn = errorColumn;
	}
	
	protected String getPosition() {
		return (errorLine != null) ? 
				"[" + errorLine + " , " + errorColumn + "]"
				: "";
	}
	
	@Override
	public String toString() {
		return id;
	}
}
