package ast;

import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

public class SPArg extends SPElementBase {

	private Type type;
	private String ID;
	// unused
	// private boolean ref;
	
	public SPArg(String type, String ID, boolean ref, int line, int column) {
		super(line, column);
		this.type = EType.getEnum(type).getType(true, ref);
		this.ID = ID;
		// unused
		// this.ref = ref;
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
	
	public Type getType() {
		return type;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type)) 
			TypeErrorsStorage.addError(new TypeError("Parameter type cannot be void", line, column));
		return EType.VOID.getType();
	}

	public String getId() {
		return ID;
	}

}
