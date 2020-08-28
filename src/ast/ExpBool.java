/*
 * 
 */
package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

/**
 * The class of boolean expressions ("true" or "false").
 */
public class ExpBool extends Exp {

	/** The value. */
	private boolean value;

	/**
	 * @param value  the value
	 * @param line   the line
	 * @param column the column
	 */
	public ExpBool(boolean value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	/**
	 * Does not have any semantic error.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return new LinkedList<SemanticError>();
	}

	/**
	 * @return the boolean type
	 */
	@Override
	public Type inferType() {
		return EType.BOOL.getType();
	}

	/**
	 * Does not have any behaviour error.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return Collections.emptyList();
	}

	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		sb.newLine("li ", "$a0 ", value ? "1" : "0");
	}
}
