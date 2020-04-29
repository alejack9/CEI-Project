package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtAssignment extends SimpleStmt {

	String id;
	SimpleStmtAssignable assign;
	
	public SimpleStmtAssignment(String id, SimpleStmtAssignable assign) {
		this.id = id;
		this.assign = assign;
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
