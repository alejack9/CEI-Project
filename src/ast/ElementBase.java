package ast;

import java.util.List;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

public abstract class ElementBase {

	protected int line, column;
	
	protected ElementBase(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	/**
	 * performs a semantic check for controlling that all declared variables exist
	 * @param e is the current environment where the information about existent variables is stored
	 * @return a list of the semantic problems found
	 */
	public abstract List<SemanticError> checkSemantics(Environment<STEntry> e);
	
	public abstract Type inferType();
	
	/**
	 * performs behavioral type inference for Simple programs
	 * @param e is the current environment where the information about existent variables is stored
	 * @return the behavior of the expression
	 */
	public abstract List<BehaviourError> inferBehaviour(Environment<BTEntry> e);
	
	public abstract void _codeGen(int nl, CustomStringBuilder sb);	
	
	public final void codeGen(int nl, CustomStringBuilder csb) {
		_codeGen(nl, csb);
	}
	
	public final String codeGen() {
		CustomStringBuilder csb = new CustomStringBuilder(new StringBuilder());
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
