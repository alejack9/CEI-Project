package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtIfThenElse extends SimpleStmt {

	SimpleStmtIfThen ifRule;
	SimpleStmtElseRule elseRule;
	
	public SimpleStmtIfThenElse(SimpleStmtIfThen ifRule, SimpleStmtElseRule elseRule) {
		this.ifRule = ifRule;
		this.elseRule = elseRule;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
}
