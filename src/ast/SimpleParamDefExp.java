package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleParamDefExp extends SimpleParamDef {

	SimpleExp exp;

	public SimpleParamDefExp(SimpleExp exp) {
		this.exp = exp;
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
