package ast;

import java.util.LinkedList;
import java.util.List;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

/**
 * The class of return statements ("return x" or "return 1+1").
 */
public class StmtRet extends Stmt {

	private Exp exp;

	private String k;

	/**
	 * @param exp           the expression to return
	 * @param argsDimension the dimension of the arguments required by the function
	 *                      that wrap the node
	 * @param line          the line in the code
	 * @param column        the column in the code
	 */
	public StmtRet(Exp exp, List<Integer> argsDimension, int line, int column) {
		super(line, column);
		this.exp = exp;
		// "8" is the value dimension of AL (4) summed to RA (4)
		k = Integer.toString(argsDimension.stream().reduce((a, b) -> a + b).orElse(0) + 8);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		if (exp != null)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * @return the type of the expression to return
	 */
	@Override
	public Type inferType() {
		return this.exp == null ? EType.VOID.getType() : exp.inferType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		if (exp != null)
			toRet.addAll(exp.inferBehaviour(e));

		return toRet;
	}

	/**
	 * Do the same actions performed by functionDec after function execution:
	 * destroy the AR.
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		if (exp != null)
			exp.codeGen(nl, sb);
		// reset $ra
		sb.newLine("lw $ra -4($fp) 4");
		// remove RA, AL and parameters from stack
		sb.newLine("addi $sp $sp ", k);
		// reset FP
		sb.newLine("lw $fp 0($sp) 4");
		// remove FP
		sb.newLine("pop 4");

		sb.newLine("jr");
	}
}
