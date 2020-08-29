/*
 * 
 */
package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
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
import ast.errors.DifferentVarTypeError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;

/**
 * The class of variable declaration statements ("int x" or "int y = 3").
 */
public class StmtDecVar extends StmtDec {

	private Type type;

	private String ID;
	private STEntry idEntry;

	private Exp exp;

	/**
	 * @param type   the type of the variable
	 * @param ID     the id of the variable
	 * @param exp    the expression on the right side
	 * @param line   the line in the code
	 * @param column the column in the code
	 */
	public StmtDecVar(Type type, String ID, Exp exp, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.exp = exp;
	}

	/**
	 * Check semantics.
	 *
	 * @param e the e
	 * @return the list
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = new STEntry(type);
		if (e.getLocalIDEntry(ID) == null)
			e.add(ID, idEntry);
		else
		// checked by behavior analysis
//			if(e.getLocalIDEntry(ID).isDeleted())
		if (e.getLocalIDEntry(ID).getType().getType().equalsTo(type))
			idEntry = e.getLocalIDEntry(ID);
		else
			toRet.add(new DifferentVarTypeError(ID, line, column));

		idEntry.setDeleted(false);

		if (exp != null)
			toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}

	/**
	 * Checks that the variable type is not VOID and checks the expression type.
	 *
	 * @return null
	 */
	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Variable type cannot be void", line, column));

		if (exp != null) {
			Type expType = exp.inferType();
			if (!type.getType().equalsTo(expType))
				TypeErrorsStorage.addError(
						new TypeError("Expression type (" + expType + ") is not equal to variable type (" + type + ")",
								this.exp.line, this.exp.column));
		}
		return null;
	}

	/**
	 * Infer behaviour.
	 */
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		BTEntry prev = e.getLocalIDEntry(ID);

		if (prev != null) {
			// if was not deleted, there's an error
			if (prev.getLocalEffect().compareTo(EEffect.D) < 0) {
				prev.setLocalEffect(EEffect.T);
				toRet.add(new IdAlreadytExistsError(ID, line, column));
			}
			// if it is not top, add the "BOTTOM" effect
			else if (prev.getLocalEffect().compareTo(EEffect.D) == 0)
				e.getIDEntry(ID).addEffect(EEffect.BOTTOM);
		} else
			e.add(ID, new BTEntry());

		if (exp != null) {
			toRet.addAll(exp.inferBehaviour(e));
			e.getIDEntry(ID).setLocalEffect(BTHelper.seq(e.getLocalIDEntry(ID).getLocalEffect(), EEffect.RW));
		}

		return toRet;
	}

	@Override
	protected void codeGen(int nl, CustomStringBuilder sb) {
		if (exp != null)
			exp.codeGen(nl, sb);
		sb.newLine("li $t1 0");
		// always saves the value of $a0 in the variable value by default
		sb.newLine("sw $a0 ", Integer.toString(idEntry.offset), "($t1) ", Integer.toString(type.getDimension()));
		// if the variable was been deleted, the code sets it to non-deleted, otherwise
		// increases the heap pointer ($hp)
		if (idEntry.isDeleted())
			idEntry.setDeleted(false);
		else
			sb.newLine("addi $hp $hp ", Integer.toString(type.getDimension()));
	}
}
