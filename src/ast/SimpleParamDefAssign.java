package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleParamDefAssign extends SimpleParamDef {

	SimpleStmtAssignableExp val;

	public SimpleParamDefAssign(SimpleStmtAssignableExp val) {
		this.val = val;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO unimplemented
				return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
				return null;
	}

}
