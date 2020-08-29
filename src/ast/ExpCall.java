package ast;

import java.util.List;

import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

/**
 * The class of call expressions ("fun()").
 */
public class ExpCall extends Exp {

	private StmtCall call;

	/**
	 * @param call   the call node
	 * @param line   the line of the code
	 * @param column the column of the code
	 */
	public ExpCall(StmtCall call, int line, int column) {
		super(line, column);
		this.call = call;
	}

	/**
	 * Delegated to the call node
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return call.checkSemantics(e);
	}

	/**
	 * Delegated to the call node
	 */
	@Override
	public Type inferType() {
		return call.inferType();
	}

	/**
	 * Delegated to the call node
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return call.inferBehaviour(e);
	}

	/**
	 * Delegated to the call node
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		call.codeGen(nl, sb);
	}
}
