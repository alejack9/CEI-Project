package ast;

import java.util.List;

import ast.errors.SemanticError;
import ast.errors.TypeError;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public abstract class SPElementBase {

	protected int line, column;
	
	protected SPElementBase(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**performs a semantic check for controlling that all declared variables exist
	 * @param e is the current environment where the information about existent variables is stored
	 * @return a list of the semantic problems found
	 */
	public abstract List<SemanticError> checkSemantics(Environment e);
	
	public abstract Type inferType() throws TypeError;
	
	/**
	 * performs behavioral type inference for Simple programs
	 * @param e is the current environment where the information about existent variables is stored
	 * @return the behavior of the expression
	 */
	public abstract BTBase inferBehavior(Environment e) throws SemanticError;

	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return column;
	}
}
