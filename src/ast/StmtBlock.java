package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.errors.TypeError;

/**
 * The class of block statements ("{ ... }").
 */
public class StmtBlock extends Stmt {

	private List<Stmt> children;

	/**
	 * Instantiate a new stmt block.
	 *
	 * @param children the children nodes list
	 * @param line     the line in the code
	 * @param column   the column in the code
	 */
	public StmtBlock(List<Stmt> children, int line, int column) {
		super(line, column);
		this.children = children;
	}

	/**
	 * Open a new scope and check the semantic of the block
	 */
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		e.openScope();
		int oldOffset = e.getOffset();
		toRet.addAll(checkSemanticsSameScope(e));

		e.closeScope();
		e.setOffset(oldOffset);

		return toRet;
	}

	/**
	 * Check the semantic of the block without opening a new scope
	 */
	public List<SemanticError> checkSemanticsSameScope(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (children != null)
			for (Stmt el : children)
				toRet.addAll(el.checkSemantics(e));
		return toRet;
	}

	/**
	 * Infer type by checking all the children.
	 *
	 * @return the type of the block or null
	 */
	@Override
	public Type inferType() {
		Type toRet = null;
		// The last return type
		EType lastRetT = null;

		// changed flag became true if the return value changes in iteration;
		// safe flag became true if an if-statement returns in only one branch.
		boolean changed = false, safe = false;

		for (Stmt child : children) {
			// Useful to rise infer type in child even if it returns null
			Type candidate = child.inferType();
			// Update the returned type if the candidate is not null
			toRet = candidate != null && !(child instanceof StmtCall) ? candidate : toRet;

			// If some expressions has returned not-null perform action
			if (toRet != null) {
				// If the previous return type is not the same as the candidate, put the flag
				// "changed" to "true"
				if (lastRetT != null && !lastRetT.equalsTo(toRet))
					changed = true;
				// Otherwise update the last returned type
				else
					lastRetT = toRet.getType();

				// If the current statement is an if-then-else ..
				if (child instanceof StmtIte) {
					// .. check that it has an "else" statement
					if (((StmtIte) child).hasElseStmt())
						safe = true;
				} else
					safe = true;
			}
		}

		// If the return value is changed during iteration, add an error
		if (changed)
			TypeErrorsStorage.addError(new TypeError("Inconsistent return types in this block", line, column));

		// If the return type is not void or null and it is not safe, add an error
		if (!safe && toRet != null && !EType.VOID.equalsTo(toRet))
			TypeErrorsStorage
					.addError(new TypeError("Unsafe return (the function does not always return)", line, column));

		return toRet;
	}

	/**
	 * Open a new scope and check the behaviour of the block.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		e.openScope();

		toRet.addAll(inferBehaviourSameScope(e));

		e.closeScope();

		return toRet;
	}

	/**
	 * Infer the behaviour of the block without opening a new scope
	 *
	 * @param e the e
	 * @return the list
	 */
	public List<BehaviourError> inferBehaviourSameScope(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		if (children != null)
			for (Stmt el : children)
				toRet.addAll(el.inferBehaviour(e));

		return toRet;
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		for (Stmt c : children)
			c.codeGen(nl, sb);
	}
}
