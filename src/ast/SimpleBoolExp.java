package ast;

import behavioural_analysis.BTBase;
import util_analysis.Environment;

/**
 * Represents a Simple Expression
 * Some child classes of this one will be SimpleExpSum, SimpleExpDiff, 
 * SimpleExpDiv, SimpleExpMult and SimpleExpNeg
 * @author Abel
 *
 */
public abstract class SimpleBoolExp implements SimpleElementBase {

	/**
	 * Calculates the value of an expression
	 * @param e the environment that stores variable values
	 * @return a boolean which is the value of the boolean expression
	 */
	public abstract boolean getValue(Environment e);
	
	@Override
	public BTBase inferBehavior(Environment e) {
		return null;
	}

}
