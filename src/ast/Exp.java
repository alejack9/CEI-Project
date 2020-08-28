/*
 * 
 */
package ast;

/**
 * The Base class for all expressions.
 */
public abstract class Exp extends ElementBase {

	protected Exp(int line, int column) {
		super(line, column);
	}
}
