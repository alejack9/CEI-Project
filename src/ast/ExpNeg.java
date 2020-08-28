/*
 * 
 */
package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpNeg.
 */
public class ExpNeg extends Exp {

	/** The exp. */
	private Exp exp;

	/**
	 * Instantiates a new exp neg.
	 *
	 * @param exp the exp
	 * @param line the line
	 * @param column the column
	 */
	public ExpNeg(Exp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	/**
	 * Check semantics.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * Infer type.
	 *
	 * @return the type
	 */
	@Override
	public Type inferType() {
		Type expT = this.exp.inferType();
		if (!EType.INT.equalsTo(expT))
			TypeErrorsStorage.addError(
					new TypeError("Expression type is \"" + expT + "\" but it must be int.", exp.line, exp.column));

		return EType.INT.getType();
	}

	/**
	 * Infer behaviour.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	/**
	 * Code gen.
	 *
	 * @param nl the nl
	 * @param sb the sb
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		exp.codeGen(nl, sb);
		sb.newLine("neg $a0");
	}
}
