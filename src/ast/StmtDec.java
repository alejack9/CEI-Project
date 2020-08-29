/*
 * 
 */
package ast;

/**
 * The base class of declaration statements.
 */
public abstract class StmtDec extends Stmt {

	/**
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	protected StmtDec(int line, int column) {
		super(line, column);
	}

}
