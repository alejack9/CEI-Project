package ast;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

public class SimpleStmtAssignment extends SimpleStmt {

	String id;
	SimpleStmtAssignable assign;
	
	public SimpleStmtAssignment(String id, SimpleStmtAssignable assign) {
		this.id = id;
		this.assign = assign;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
			
		if (!e.containsVariable(id))
			toRet.add(new SemanticError(Strings.ErrorVariableDoesntExist));
		
		toRet.addAll(assign.checkSemantics(e));
		
		return toRet;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
