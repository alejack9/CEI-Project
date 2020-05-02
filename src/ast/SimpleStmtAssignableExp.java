package ast;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtAssignableExp extends SimpleStmtAssignable {
	SimpleExp exp;

	public SimpleStmtAssignableExp(SimpleExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment e) {
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
		return null;
	}

}
