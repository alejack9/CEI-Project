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

public class ExpVar extends Exp {

	private String id;
	private STEntry idEntry;

	public ExpVar(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}

	/**
	 * Checks if the variable in use exists. if it doesn't then add an error.
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(id);

		if (idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));

		return toRet;
	}

	@Override
	public Type inferType() {
		return this.idEntry.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		BTEntry current = e.getIDEntry(id);
		
		if(EEffect.T.compareTo(current.getLocalEffect()) == 0)
			return toRet;

		current.setLocalEffect(BTHelper.seq(current.getLocalEffect(), EEffect.RW));

		if (e.getIDEntry(id).getLocalEffect().compareTo(EEffect.T) == 0)
			toRet.add(new DeletedVariableError(id, line, column));

		return toRet;
	}

	public String getId() {
		return id;
	}

	public STEntry getIdEntry() {
		return idEntry;
	}

	// scrive sempre il valore in $a0
	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		if (idEntry.getType().isParameter()) {
			CodeGenUtils.getVariableCodeGen(idEntry, nl, sb);

			if (idEntry.getType().isRef()) {
				sb.newLine("lw $a0 0($a0) 4");
			}
		} else {
			sb.newLine("li $t1 0");
			sb.newLine("lw $a0 ", Integer.toString(idEntry.offset), "($t1) ",
					Integer.toString(idEntry.getType().getDimension()));
		}
	}
}
