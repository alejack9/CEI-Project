package ast;

import java.util.Collections;
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

	private boolean value;

	/**
	 * @param value  the boolean value
	 * @param line   the line of the code
	 * @param column the column of the code
	 */
	public ExpBool(boolean value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	/**
	 * Do not have any semantic error.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return Collections.emptyList();
	}

	/**
	 * @return {@link EType#BOOL BOOL} as type
	 */
	@Override
	public Type inferType() {
		return EType.BOOL.getType();
	}

	/**
	 * Do not have any behaviour error.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return Collections.emptyList();
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		sb.newLine("li ", "$a0 ", value ? "1" : "0");
	}
}
