package ast;

import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleReturnVar extends SimpleStmtReturnRule {
	String ID;

	public SimpleReturnVar(String ID) {
		this.ID = ID;
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
