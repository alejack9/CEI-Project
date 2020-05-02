package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleParamDefVarRef extends SimpleParamDef {

	SimpleVariableRef ref;

	public SimpleParamDefVarRef(SimpleVariableRef ref) {
		this.ref = ref;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return ref.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
				return null;
	}

}
