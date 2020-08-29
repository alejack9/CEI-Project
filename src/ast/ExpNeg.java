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

/**
 * The class of negative expressions ("-x")
 */
public class ExpNeg extends Exp {

	private Exp exp;

	/**
	 * @param exp    the expression to negate
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpNeg(Exp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		toRet.addAll(exp.checkSemantics(e));
		return toRet;
	}

	/**
	 * Check that the type of the expression is {@link EType#INT INT}.
	 * 
	 * @return {@link EType#INT INT} as type
	 */
	@Override
	public Type inferType() {
		Type expT = this.exp.inferType();
		if (!EType.INT.equalsTo(expT))
			TypeErrorsStorage.addError(
					new TypeError("Expression type is \"" + expT + "\" but it must be int.", exp.line, exp.column));

		return EType.INT.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		exp.codeGen(nl, sb);
		sb.newLine("neg $a0");
	}
}
