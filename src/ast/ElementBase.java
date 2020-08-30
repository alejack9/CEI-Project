/*
 * 
 */
package ast;

import java.util.List;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

/**
 * The Base class for all elements.
 */
public abstract class ElementBase {

	protected int line, column;

	/**
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	protected ElementBase(int line, int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * Check basic semantic errors.
	 *
	 * @param e is the current environment where information about existent
	 *          variables is stored
	 * @return a list of the semantic errors found
	 */
	public abstract List<SemanticError> checkSemantics(Environment<STEntry> e);

	/**
	 * Infer type.
	 *
	 * @return null if it does not have any type (void is reserved for return
	 *         statements), the type of the element otherwise
	 */
	public abstract Type inferType();

	/**
	 * Perform behavioural analysis.
	 *
	 * @param e the environment
	 * @return the behaviour of the expression
	 */
	public abstract List<BehaviourError> inferBehaviour(Environment<BTEntry> e);

	public abstract void codeGen(int nl, CustomStringBuilder csb);

}
