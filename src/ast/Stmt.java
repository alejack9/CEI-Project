/*
 * 
 */
package ast;

/**
 * The Base class for all statements.
 */
public abstract class Stmt extends ElementBase {

	protected Stmt(int line, int column) {
		super(line, column);
	}

}
