package ast;

import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

/**
 * The class of print statements ("print x" or "print 1+1").
 */
public class StmtPrint extends Stmt {

	private Exp exp;

	/**
	 * @param exp    the expression to print
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtPrint(Exp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return exp.checkSemantics(e);
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	/**
	 * Check that the expression is not a {@link EType#VOID VOID} type.
	 *
	 * @return null
	 */
	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Cannot print void expression", this.exp.line, this.exp.column));
		return null;
	}

	/**
	 * The assembly instruction "print" prints the value stored in $a0.
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		exp.codeGen(nl, sb);
		sb.newLine("print");
	}

}
