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

public class StmtDecVar extends StmtDec {

	private Type type;
	private String ID;
	private Exp exp;
	private STEntry idEntry;

	public StmtDecVar(Type type, String ID, Exp exp, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.exp = exp;
	}

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

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();

		BTEntry prev = e.getLocalIDEntry(ID);

		// Should be added after the exp check (if any) but this needs two "exp == null"
		// controls instead of one
		// "checkSemantics" checks the correct usage of the variable so we can skip the
		// check
		if (prev != null) {
			if (prev.getLocalEffect().compareTo(EEffect.D) < 0) {
				prev.setLocalEffect(EEffect.T);
				toRet.add(new IdAlreadytExistsError(ID, line, column));
			} else if (prev.getLocalEffect().compareTo(EEffect.D) == 0)
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
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Variable type cannot be void", line, column));

		if (exp != null) {
			Type expType = exp.inferType();
			if (!expType.getType().equalsTo(type))
				TypeErrorsStorage.addError(
						new TypeError("Expression type (" + expType + ") is not equal to variable type (" + type + ")",
								this.exp.line, this.exp.column));
		}

		return null;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		if (exp != null)
			exp._codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("sw $a0 ", Integer.toString(idEntry.offset), "($t1) ", Integer.toString(type.getDimension()));
		if (idEntry.isDeleted())
			idEntry.setDeleted(false);
		else
			sb.newLine("addi $hp $hp ", Integer.toString(type.getDimension()));
	}

}
