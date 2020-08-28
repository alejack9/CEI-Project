/*
 * 
 */
package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpNot.
 */
public class ExpNot extends Exp {

	/** The exp. */
	private Exp exp;

	/**
	 * Instantiates a new exp not.
	 *
	 * @param exp the exp
	 * @param line the line
	 * @param column the column
	 */
	public ExpNot(Exp exp, int line, int column) {
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
		if (!EType.BOOL.equalsTo(expT))
			TypeErrorsStorage.addError(
					new TypeError("Expression type is \"" + expT + "\" but it must be bool.", exp.line, exp.column));

		return EType.BOOL.getType();
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
		String one = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		exp.codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", one);
		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(one, ":");
		sb.newLine("li $a0 1");
		sb.newLine(end, ":");
	}

}
