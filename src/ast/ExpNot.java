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

/**
 * The class of not expressions ("!x")
 */
public class ExpNot extends Exp {

	private Exp exp;

	/**
	 * @param exp    the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpNot(Exp exp, int line, int column) {
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
	 * Check that the type of the expression is {@link EType#BOOL BOOL}.
	 * 
	 * @return {@link EType#BOOL BOOL} as type
	 */
	@Override
	public Type inferType() {
		Type expT = this.exp.inferType();
		if (!EType.BOOL.equalsTo(expT))
			TypeErrorsStorage.addError(
					new TypeError("Expression type is \"" + expT + "\" but it must be bool.", exp.line, exp.column));

		return EType.BOOL.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		String trueLabel = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		exp.codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", trueLabel);
		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(trueLabel, ":");
		sb.newLine("li $a0 1");
		sb.newLine(end, ":");
	}

}
