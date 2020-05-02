package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

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
			toRet.add(new VariableNotExistsError(id));
		
		toRet.addAll(assign.checkSemantics(e));
		
		return toRet;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
