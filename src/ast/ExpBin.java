/*
 * 
 */
package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

/**
 * The Base class for all the binary expression.
 */
public abstract class ExpBin extends Exp {

	/** The left side of the expression. */
	protected Exp leftSide;
	/** The right side of the expression. */
	protected Exp rightSide;

	/**
	 * @param left   the left side of the expression
	 * @param right  the right side of the expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	protected ExpBin(Exp left, Exp right, int line, int column) {
		super(line, column);
		this.leftSide = left;
		this.rightSide = right;
	}

	/**
	 * Gets the operation as String.
	 *
	 * @return the operation as String
	 */
	protected abstract String getOp();

	/**
	 * Check left and right side semantics.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public final List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));
		return toRet;
	}

	/**
	 * Infer behavior of left and right side of expression.
	 *
	 * @param e the environment
	 * @return the list of behavior error
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		toRet.addAll(leftSide.inferBehaviour(e));
		toRet.addAll(rightSide.inferBehaviour(e));
		return toRet;
	}
}
