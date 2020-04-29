package ast;

import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtIfThen extends SimpleStmt {
	SimpleBoolExp exp;
	SimpleStmtBlock block;

	public SimpleStmtIfThen(SimpleBoolExp exp, SimpleStmtBlock block) {
		this.exp = exp;
		this.block = block;
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
