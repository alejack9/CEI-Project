package ast;

import java.util.List;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.types.Type;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

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
	public abstract List<SemanticError> checkSemantics(Environment<STEntry> e);
	
	public abstract Type inferType();
	
	/**
	 * performs behavioral type inference for Simple programs
	 * @param e is the current environment where the information about existent variables is stored
	 * @return the behavior of the expression
	 */
	public abstract List<BehaviourError> inferBehaviour(Environment<BTEntry> e);
	
//	public abstract String codeGen(int nl);
	
//	public final String codeGen() {
//		return codeGen(-1);
//	}
}
