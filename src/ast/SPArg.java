package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPArg implements SPElementBase {

	private Type type;
	private String ID;
	private boolean ref;
	
	public SPArg(String type, String ID, boolean ref) {
		this.type = EType.getEnum(type).getType(true, ref);
		this.ID = ID;
		this.ref = ref;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// Checked by "SPStmtDecFun"
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Type getType() {
		return type;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type)) throw new TypeError("Parameter type cannot be void");
		return EType.VOID.getType();
	}

	public String getId() {
		return ID;
	}

}
