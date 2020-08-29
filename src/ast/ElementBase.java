/*
 * 
 */
package ast;

import java.util.List;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

/**
 * The Base class for all elements.
 */
public abstract class ElementBase {

	protected int line, column;

	/**
	 * 
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	protected ElementBase(int line, int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * checks basic semantic errors.
	 *
	 * @param e is the current environment where information about existent
	 *          variables is stored
	 * @return a list of the semantic errors found
	 */
	public abstract List<SemanticError> checkSemantics(Environment<STEntry> e);

	/**
	 * Infer type.
	 *
	 * @return null if it does not have any type (void is reserved for return
	 *         statements), the type of the element otherwise
	 */
	public abstract Type inferType();

	/**
	 * performs behavioral analysis.
	 *
	 * @param e the environment
	 * @return the behavior of the expression
	 */
	public abstract List<BehaviourError> inferBehaviour(Environment<BTEntry> e);

	protected abstract void codeGen(int nl, CustomStringBuilder csb);

	/**
	 * Generates the outCode basing on the code having this element as parent.
	 */
	public final String codeGen() {
		CustomStringBuilder csb = new CustomStringBuilder(new StringBuilder());
		/**
		 * Wraps the actual code in a starting AR to avoid errors in case of "return"
		 * statement in main block
		 */
		csb.newLine("b CALLMAIN");
		csb.newLine("MAIN:");
		csb.newLine("move $fp $sp");
		csb.newLine("push $ra 4");
		csb.newLine();

		codeGen(0, csb);

		csb.newLine();
		csb.newLine("lw $ra 0($sp) 4");
		csb.newLine("addi $sp $sp 8");
		csb.newLine("lw $fp 0($sp) 4");
		csb.newLine("pop 4");
		csb.newLine("jr");
		csb.newLine("CALLMAIN:");
		csb.newLine("push $fp 4");
		csb.newLine("move $al $fp");
		csb.newLine("push $al 4");
		csb.newLine("jal MAIN");

		return csb.toString();
	}
}
