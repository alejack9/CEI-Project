package ast;

import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

/**
 * Parameter of a function
 */
public class Arg extends ElementBase {

	private Type type;

	private String ID;

	/**
	 * Instantiate a new parameter.
	 *
	 * @param type   the type
	 * @param ID     the id
	 * @param ref    true if it is a reference, false otherwise
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public Arg(String type, String ID, boolean ref, int line, int column) {
		super(line, column);
		this.type = EType.getEnum(type).getType(true, ref);
		this.ID = ID;
	}

	/**
	 * @return null (because it is checked by "SPStmtDecFun")
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return null;
	}

	/**
	 * @return null because it does not have any type (void is reserved for return
	 *         statements)
	 */
	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Parameter type cannot be void", line, column));
		return null;
	}

	/**
	 * @return null (because it is checked by "SPStmtDecFun")
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return null;
	}

	/**
	 * Do not do anything because it is not necessary
	 *
	 * @param nl the nesting level
	 * @param sb the (custom) string builder
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
	}

	/**
	 * Get the type.
	 *
	 * @return the type of the parameter
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return ID;
	}

}
