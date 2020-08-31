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

/**
 * The class of if-then-else statements<br>
 * ("if(e) { ... }" or "if(e) { ... } else { ... }").
 */
public class StmtIte extends Stmt {

	private Exp exp;

	private Stmt thenStmt, elseStmt;

	/**
	 * @param exp      the boolean expression in "if"
	 * @param thenStmt the then statement
	 * @param elseStmt the else statement
	 * @param line     the line in the code
	 * @param column   the column in the code
	 */
	public StmtIte(Exp exp, Stmt thenStmt, Stmt elseStmt, int line, int column) {
		super(line, column);
		this.exp = exp;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}

	/**
	 * Check then and else branches using passed environment's clones.
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
	 * @return the type of the then branch
	 */
	@Override
	public Type inferType() {
		if (!EType.BOOL.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Condition must be bool type", exp.line, exp.column));

		Type thenT = this.thenStmt.inferType();

		if (elseStmt == null)
			return thenT;

		Type elseT = this.elseStmt.inferType();

		// If elseT is null and thenT is not null or otherwise, then add an error.
		// Add an error also if the branch types does not correspond
		if (elseT != null && !elseT.getType().equalsTo(thenT) || thenT != null && !thenT.getType().equalsTo(elseT))
			TypeErrorsStorage.addError(new TypeError(
					"Then branch (" + thenT + ") does not return the same type of else branch (" + elseT + ")",
					this.thenStmt.line, this.thenStmt.column));

		return thenT;
	}

	/**
	 * Check the behaviour of then and else branch with two different environments
	 * and, if there was an "else" statement, performs a "max" operation between the
	 * computed environments.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		toRet.addAll(exp.inferBehaviour(e));

		Environment<BTEntry> tempE = null;

		if (elseStmt != null) {
			// Perform "clone" only if it's necessary
			tempE = (Environment<BTEntry>) e.clone();
			analyseStmtBehaviour(tempE, elseStmt, toRet);
		}

		analyseStmtBehaviour(e, thenStmt, toRet);

		if (tempE != null)
			BTHelper.maxModifyEnv(e, tempE);

		return toRet;
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
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
	 * If there's not a scope in the branch, open and close a scope before and after
	 * analyse the behaviour (in order to always have a new scope for the branches
	 * operations).
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
	 * @return true, if the element has an else branch
	 */
	public boolean hasElseStmt() {
		return elseStmt != null;
	}
}
