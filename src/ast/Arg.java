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

public class Arg extends ElementBase {

	private Type type;
	private String ID;

	public Arg(String type, String ID, boolean ref, int line, int column) {
		super(line, column);
		this.type = EType.getEnum(type).getType(true, ref);
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		// Checked by "SPStmtDecFun"
		return null;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		// Checked by "SPStmtDecFun"
		return null;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Parameter type cannot be void", line, column));
		return null;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
	}

	public Type getType() {
		return type;
	}

	public String getId() {
		return ID;
	}

}
