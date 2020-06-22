package ast;

import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtAssignment extends SPStmt{
	String id;
	SPExp exp;

	public SPStmtAssignment(String id, SPExp exp) {
		this.id = id;
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		
		List<SemanticError> res = exp.checkSemantics(e);
		
//		e.addVariable(id, exp.getValue(e));
		return res;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		int cost ;
		//if the variable doesn't exist in the current scope then 
		//it has a cost equals to 1
		if(e.getLocalIDEntry(id) == null)
			cost = 1;
		else cost = 0 ;
		
		//put the variable in the current scope with the current value
		e.addVariable(id, exp.getValue(e));
		
		return new BTAtom(cost);
	}

	@Override
	public SPElementBase inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
