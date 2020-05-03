package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class StmtAssignableExp extends StmtAssignable {
	Exp exp;

	public StmtAssignableExp(Exp exp) {
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
