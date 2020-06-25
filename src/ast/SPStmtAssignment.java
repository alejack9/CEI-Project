package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtAssignment extends SPStmt{
	String id;
	STEntry idEntry;
	SPExp exp;

	public SPStmtAssignment(String id, SPExp exp) {
		this.id = id;
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO populate idEntry
		
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
		// e.addVariable(id, exp.getValue(e));
		
		return new BTAtom(cost);
	}

	@Override
	public Type inferType() {
		Type expType = exp.inferType(); 
		if(expType.getType().equalsTo(idEntry.getType()))
			throw new TypeError("Variable type (" + idEntry.getType() + ") is not equal to expression type (" + expType +")");
		
		return EType.VOID.getType();
	}

}
