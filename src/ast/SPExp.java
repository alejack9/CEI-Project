package ast;

import java.util.List;
import ast.errors.BehaviourError;
import util_analysis.Environment;

/**
 * Represents a Simple Expression
 * Some child classes of this one will be SimpleExpSum, SimpleExpDiff, 
 * SimpleExpDiv, SimpleExpMult and SimpleExpNeg
 * @author Abel
 *
 */
public abstract class SPExp extends SPElementBase {

	protected SPExp(int line, int column) {
		super(line, column);
	}

	/**
	 * Calculates the value of an expression
	 * @param e the environment that stores variable values
	 * @return an integer which is the value of the expression
	 */
	public abstract int getValue(Environment e);
	
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		
		return null;
	}

}
