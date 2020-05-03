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
public abstract class Cond extends BoolExp {
	
	@Override
	public BTBase inferBehavior(Environment e) {
		
		return null;
	}

}
