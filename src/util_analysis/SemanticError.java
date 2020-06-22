package util_analysis;

public class SemanticError extends Error {
	
	private static final long serialVersionUID = 1L;
	String errorDescription;
	
	//it should be good to have also 
	//int errorLine;
	//int errorColumn;
	
	public SemanticError(String errorDescription){
		this.errorDescription = errorDescription;
	}
	
	@Override
	public String toString() {
		return errorDescription;
	}
}
