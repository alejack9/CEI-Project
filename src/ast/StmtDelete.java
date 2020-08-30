package ast;

import java.util.LinkedList;
import java.util.List;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.SemanticError;
import ast.errors.TypeError;
import ast.errors.VariableAlreadyDeletedError;
import ast.errors.VariableNotExistsError;
import ast.errors.VariableNotVarError;

/**
 * The class of delete statements ("delete x").
 */
public class StmtDelete extends Stmt {

	private String id;
	private STEntry idEntry;

	/**
	 * @param id     the id of the variable to delete
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtDelete(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}

	/**
	 * Search for the variable in the local and the global scope.<br />
	 * If the variable is local, then check if it is not deleted and is not a
	 * parameter: if so, set it as deleted and exit;<br />
	 * Otherwise, check in all scopes and, if the found variable is deleted, add an
	 * error and exit. If the variable is a reference or is not a parameter, then
	 * set it as deleted
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		STEntry candidate = e.getLocalIDEntry(id);

		if (candidate != null && !candidate.isDeleted() && !candidate.getType().isParameter()) {
			idEntry = e.deleteVariable(id);
			return toRet;
		}

		// candidate is not local
		candidate = e.getIDEntry(id);
		if (candidate == null) {
			toRet.add(new VariableNotExistsError(id, line, column));
			return toRet;
		}
		if (candidate.isDeleted()) {
			toRet.add(new VariableAlreadyDeletedError(id, line, column));
			return toRet;
		}

		if (candidate.getType().isRef() || !candidate.getType().isParameter()) {
			idEntry = e.deleteVariable(id);
			return toRet;
		}

		toRet.add(new VariableNotVarError(id, line, column));

		return toRet;
	}

	/**
	 * Check that the user does not require to delete a function.
	 *
	 * @return null
	 */
	@Override
	public Type inferType() {
		if (EType.FUNCTION.equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Cannot delete a function", line, column));
		return null;
	}

	/**
	 * Set the local effect of the pointed variable to "D" and, if its effect is
	 * "D", add an error.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		e.getIDEntry(id).setLocalEffect(BTHelper.seq(e.getIDEntry(id).getLocalEffect(), EEffect.D));

		if (e.getIDEntry(id).getLocalEffect().equals(EEffect.T))
			toRet.add(new DeletedVariableError(id, line, column));

		return toRet;
	}

	/**
	 * Set the pointed variable as deleted
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		idEntry.setDeleted(true);
	}
}
