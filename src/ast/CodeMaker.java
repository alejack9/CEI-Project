package ast;

import support.CustomStringBuilder;

public class CodeMaker {
	private StmtBlock block;

	public CodeMaker(StmtBlock block) {
		this.block = block;
	}

	public String codeGen() {
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

		block.codeGen(0, csb);

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
