/*
 * 
 */
package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
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
 * The Class StmtIte.
 */
public class StmtIte extends Stmt {

	/** The exp. */
	private Exp exp;
	
	/** The else stmt. */
	private Stmt thenStmt, elseStmt;

	/**
	 * Instantiates a new stmt ite.
	 *
	 * @param exp the exp
	 * @param thenStmt the then stmt
	 * @param elseStmt the else stmt
	 * @param line the line
	 * @param column the column
	 */
	public StmtIte(Exp exp, Stmt thenStmt, Stmt elseStmt, int line, int column) {
		super(line, column);
		this.exp = exp;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}

	/**
	 * Check semantics.
	 *
	 * @param e the e
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		toRet.addAll(exp.checkSemantics(e));

		if (elseStmt != null)
			toRet.addAll(elseStmt.checkSemantics((Environment<STEntry>) e.clone()));

		toRet.addAll(thenStmt.checkSemantics((Environment<STEntry>) e.clone()));

		return toRet;

	}

	/**
	 * Infer behaviour.
	 *
	 * @param e the e
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		toRet.addAll(exp.inferBehaviour(e));

		Environment<BTEntry> tempE = null;

		if (elseStmt != null) {
			tempE = (Environment<BTEntry>) e.clone();
			analyseStmtBehaviour(tempE, elseStmt, toRet);
		}

		analyseStmtBehaviour(e, thenStmt, toRet);

		if (tempE != null)
			BTHelper.maxModifyEnv(e, tempE);

		return toRet;
	}

	/**
	 * Analyse stmt behaviour.
	 *
	 * @param e the e
	 * @param stmt the stmt
	 * @param errors the errors
	 */
	private void analyseStmtBehaviour(Environment<BTEntry> e, Stmt stmt, List<BehaviourError> errors) {
		if (stmt instanceof StmtBlock)
			errors.addAll(stmt.inferBehaviour(e));
		else {
			e.openScope();
			errors.addAll(stmt.inferBehaviour(e));
			e.closeScope();
		}
	}

	/**
	 * Infer type.
	 *
	 * @return the type
	 */
	@Override
	public Type inferType() {
		if (!EType.BOOL.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Condition must be bool type", exp.line, exp.column));

		Type thenT = this.thenStmt.inferType();

		if (elseStmt == null)
			return thenT;

		Type elseT = this.elseStmt.inferType();

		if (elseT != null && !elseT.getType().equalsTo(thenT)
				|| thenT != null && !thenT.getType().equalsTo(elseT))
			TypeErrorsStorage.addError(new TypeError(
					"Then branch (" + thenT + ") does not return the same type of else branch (" + elseT + ")",
					this.thenStmt.line, this.thenStmt.column));

		return thenT;
	}

	/**
	 * Code gen.
	 *
	 * @param nl the nl
	 * @param sb the sb
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		String T = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();

		exp.codeGen(nl, sb);
		sb.newLine("li $t1 1");
		sb.newLine("beq $a0 $t1 ", T);
		if (elseStmt != null)
			elseStmt.codeGen(nl, sb);
		sb.newLine("b ", end);
		sb.newLine(T, ":");
		thenStmt.codeGen(nl, sb);
		sb.newLine(end, ":");
	}

	/**
	 * Checks for else stmt.
	 *
	 * @return true, if successful
	 */
	public boolean hasElseStmt() {
		return elseStmt != null;
	}
}
