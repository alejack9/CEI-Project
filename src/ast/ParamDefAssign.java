package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
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

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
				return null;
	}

}
