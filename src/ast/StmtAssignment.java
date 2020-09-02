package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.errors.VariableNotExistsError;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.SemanticError;

/**
 * The class of assignment statements ("x = 3" or "x = y").
 */
public class StmtAssignment extends Stmt {

	private String id;

	private STEntry idEntry;

	private Exp exp;

	/**
	 * @param id     the variable id
	 * @param exp    the right expression
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtAssignment(String id, Exp exp, int line, int column) {
		super(line, column);
		this.id = id;
		this.exp = exp;
	}

	/**
	 * Check that the variable exists and check the right expression semantic.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(id);

		if (idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));

		toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * Check that the variable type is the same as the expression type.
	 *
	 * @return null
	 */
	@Override
	public Type inferType() {
		Type expType = exp.inferType();
		if (!expType.getType().equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError(
					"Variable type (" + idEntry.getType() + ") is not equal to expression type (" + expType + ")", line,
					column));

		return null;
	}

	/**
	 * Infer expression behaviour and than check that the entry is not in
	 * {@link EEffect#T TOP} state.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		toRet.addAll(exp.inferBehaviour(e));

		/**
		 * Set the local effect to the id local effect seq by {@link EEffect#RW RW}
		 * idLocalEffect = idLocalEffect ▷ RW
		 */
		e.getIDEntry(id).setLocalEffect(BTHelper.seq(e.getIDEntry(id).getLocalEffect(), EEffect.RW));

		if (e.getIDEntry(id).getLocalEffect().equals(EEffect.T))
			toRet.add(new DeletedVariableError(id, line, column));

		return toRet;
	}

	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		/** Generate the expression code */
		exp.codeGen(nl, sb);

		/** If the entry is not a parameter, save the $a0 value in the heap */
		if (!idEntry.getType().isParameter()) {
			/**
			 * $t1=0 is only used to tell to the executor to get the variable from the heap
			 */
			sb.newLine("li $t1 0");
			/**
			 * Save the $a0 value into the heap at the position indicated by the offset of
			 * idEntry
			 */
			sb.newLine("sw $a0 ", Integer.toString(idEntry.offset), "($t1) ",
					Integer.toString(idEntry.getType().getDimension()));
			return;
		}

		// The variable is a parameter

		sb.newLine("move $t1 $a0");
		/**
		 * Generate code to get the variable value<br>
		 * NOTE: it also changes the $al value pointing to the correct scope
		 */
		CodeGenUtils.getVariableCodeGen(idEntry, nl, sb);

		/** Prepare $t1 to be saved somewhere */
		sb.newLine("sw $t1 ");

		/**
		 * If the variable is a reference, save in the address retrieved by the variable
		 * value
		 */
		// NOTE: if the variable is a reference, we need to point to the address
		// written in the $a0 value
		if (idEntry.getType().isRef())
			sb.sameRow("0($a0) ");
		/** Otherwise save in the offset of variable starting from $al value */
		else
			sb.sameRow(Integer.toString(idEntry.offset), "($al) ");

		/** Add the dimension of the variable to save */
		sb.sameRow(Integer.toString(idEntry.getType().getDimension()));
	}
}
