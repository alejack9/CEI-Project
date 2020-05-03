package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class StmtAssignableVar extends StmtAssignable {
	String ID;

	public StmtAssignableVar(String ID) {
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariable(ID))
			toRet.add(new VariableNotExistsError(ID));
		return toRet;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		return null;
//		int cost ;
//		//if the variable doesn't exist in the current scope then 
//		//it has a cost equals to 1
//		if(e.getVariableValueLocal(id) == null)
//			cost = 1;
//		else cost = 0 ;
//		
//		//put the variable in the current scope with the current value
//		e.addVariable(id, exp.getValue(e));
//		
//		return new BTAtom(cost);
	}

}
