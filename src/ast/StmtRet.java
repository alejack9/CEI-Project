/*
 * 
 */
package ast;

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

// TODO: Auto-generated Javadoc
/**
 * The Class StmtRet.
 */
public class StmtRet extends Stmt {

	/** The exp. */
	private Exp exp;
	
	/** The k. */
	private String k;

	/**
	 * Instantiates a new stmt ret.
	 *
	 * @param exp the exp
	 * @param argsDimension the args dimension
	 * @param line the line
	 * @param column the column
	 */
	public StmtRet(Exp exp, List<Integer> argsDimension, int line, int column) {
		super(line, column);
		this.exp = exp;
		k = Integer.toString(argsDimension.stream().reduce((a, b) -> a + b).orElse(0) + 8);
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

		if (exp != null)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * Infer behaviour.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		if (exp != null)
			toRet.addAll(exp.inferBehaviour(e));

		return toRet;
	}

	/**
	 * Infer type.
	 *
	 * @return the type
	 */
	@Override
	public Type inferType() {
		Type toRet = this.exp == null ? EType.VOID.getType() : exp.inferType();

		return toRet;
	}

	/**
	 * Code gen.
	 *
	 * @param nl the nl
	 * @param sb the sb
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		if (exp != null)
			exp.codeGen(nl, sb);
		sb.newLine("lw $ra -4($fp) 4");
		sb.newLine("addi $sp $sp ", k);
		sb.newLine("lw $fp 0($sp) 4");
		// as the pop of functionDec
		sb.newLine("pop 4");
		sb.newLine("jr");
	}
}
