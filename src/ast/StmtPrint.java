/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class StmtPrint.
 */
public class StmtPrint extends Stmt {

	/** The exp. */
	private Exp exp;

	/**
	 * Instantiates a new stmt print.
	 *
	 * @param exp the exp
	 * @param line the line
	 * @param column the column
	 */
	public StmtPrint(Exp exp, int line, int column) {
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
		return exp.checkSemantics(e);
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
	 * Infer type.
	 *
	 * @return the type
	 */
	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Cannot print void expression", this.exp.line, this.exp.column));
		return null;
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
		sb.newLine("print");
	}

}
