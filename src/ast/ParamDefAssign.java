package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class ParamDefAssign extends ParamDef {

	StmtAssignableExp val;

	public ParamDefAssign(StmtAssignableExp val) {
		this.val = val;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return val.checkSemantics(e);
	}

}
