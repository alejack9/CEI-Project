/*
 * 
 */
package ast;

import java.util.List;

import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpCall.
 */
public class ExpCall extends Exp {

	/** The call. */
	private StmtCall call;

	/**
	 * Instantiates a new exp call.
	 *
	 * @param call the call
	 * @param line the line
	 * @param column the column
	 */
	public ExpCall(StmtCall call, int line, int column) {
		super(line, column);
		this.call = call;
	}

	/**
	 * Check semantics.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return call.checkSemantics(e);
	}

	/**
	 * Infer type.
	 *
	 * @return the type
	 */
	@Override
	public Type inferType() {
		return call.inferType();
	}

	/**
	 * Infer behaviour.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return call.inferBehaviour(e);
	}

	/**
	 * Code gen.
	 *
	 * @param nl the nl
	 * @param sb the sb
	 */
	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		call.codeGen(nl, sb);
	}
}
