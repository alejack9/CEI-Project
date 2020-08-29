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
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.errors.TypeError;

/**
 * The class of block expressions ("{ ... }").
 */
public class StmtBlock extends Stmt {

	private List<Stmt> children;

	/**
	 * Instantiates a new stmt block.
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
	 * Opens a new scope and checks the semantic of the block
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
	 * Checks the semantic of the block without opening a new scope
	 */
	public List<SemanticError> checkSemanticsSameScope(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (children != null)
			for (Stmt el : children)
				toRet.addAll(el.checkSemantics(e));
		return toRet;
	}

	/**
	 * Infers type by checking all the children.
	 *
	 * @return the type of the block or null
	 */
	@Override
	public Type inferType() {
		Type toRet = null;
		// the last return type
		EType lastRetT = null;

		/*
		 * int g() {
		 *    return g();
		 * }
		 * int f() {
		 *    g();
		 *    print x;
		 * }
		 */
		
		boolean changed = false, safe = false;

		for (Stmt c : children) {
			// useful to infer type even if it returns null
			Type candidate = c.inferType();
			// updates the returned type if the candidate is not null
			toRet = candidate != null && !(c instanceof StmtCall) ? candidate : toRet;

			// if some expressions has returned not-null
			if (toRet != null) {
				// if the previous return type is not the same as the candidate, puts the flage
				// "changed" to "true"
				if (lastRetT != null && !lastRetT.equalsTo(toRet))
					changed = true;
				// otherwise updates the last returned type
				else
					lastRetT = toRet.getType();

				// if the current statement is an if-then-else ..
				if (c instanceof StmtIte) {
					// .. checks that it has an "else" statement
					if (((StmtIte) c).hasElseStmt())
						safe = true;
				} else
					safe = true;
			}
		}

		// if the return value is changed during iteration, add an error
		if (changed)
			TypeErrorsStorage.addError(new TypeError("Inconsistent return types in this block", line, column));

		// if the return type is not void or null and it is not safe, add an error
		if (!safe && toRet != null && !EType.VOID.equalsTo(toRet))
			TypeErrorsStorage
					.addError(new TypeError("Unsafe return (the function does not always return)", line, column));

		return toRet;
	}

	/**
	 * Opens a new scope and checks the behaviour of the block.
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
	protected void codeGen(int nl, CustomStringBuilder sb) {
		for (Stmt c : children)
			c.codeGen(nl, sb);
	}
}
