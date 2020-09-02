package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;

/**
 * The variable expression class ("x" or "y").
 */
public class ExpVar extends Exp {

	private String id;

	/** Filled in {@link ExpVar#checkSemantics(Environment) checkSemantics}. */
	private STEntry idEntry;

	/**
	 * @param id     the variable id
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public ExpVar(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}

	/**
	 * Check if the variable does exist.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		/** Fills idEntry */
		idEntry = e.getIDEntry(id);

		if (idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));

		return toRet;
	}

	/**
	 * @return the type extracted by idEntry
	 */
	@Override
	public Type inferType() {
		return this.idEntry.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		BTEntry current = e.getIDEntry(id);

		/**
		 * Set the local effect to the current local effect seq by {@link EEffect#RW RW}
		 * currentLocalEffect = currentLocalEffect ▷ RW
		 */
		current.setLocalEffect(BTHelper.seq(current.getLocalEffect(), EEffect.RW));

		/** If the previous operation caused an error, add the error */
		if (e.getIDEntry(id).getLocalEffect().compareTo(EEffect.T) == 0)
			toRet.add(new DeletedVariableError(id, line, column));

		return toRet;
	}

	/**
	 * Always write in $a0 the actual value of the variable.
	 */
	@Override
	public void codeGen(int nl, CustomStringBuilder sb) {
		if (idEntry.getType().isParameter()) {
			CodeGenUtils.getVariableCodeGen(idEntry, nl, sb);

			if (idEntry.getType().isRef())
				sb.newLine("lw $a0 0($a0) 4");
		} else {
			sb.newLine("li $t1 0");
			sb.newLine("lw $a0 ", Integer.toString(idEntry.offset), "($t1) ",
					Integer.toString(idEntry.getType().getDimension()));
		}
	}

	public String getId() {
		return id;
	}

	public STEntry getIdEntry() {
		return idEntry;
	}
}
