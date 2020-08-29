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
 * The integer value expression class ("4" or "1").
 */
public class ExpVal extends Exp {

	private int value;

	/**
	 * @param value  the value
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpVal(int value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	/**
	 * No semantic errors returned.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return new LinkedList<SemanticError>();
	}

	/**
	 * @return {@link EType#INT INT} as type.
	 */
	@Override
	public Type inferType() {
		return EType.INT.getType();
	}

	/**
	 * No behaviour errors returned.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return Collections.emptyList();
	}

	/**
	 * Loads the value in $a0
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		sb.newLine("li $a0 ", Integer.toString(value));
	}
}
