package ast;

import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtDeclarationAssignment extends SimpleStmt {
	SimpleStmtDeclaration declaration;
	SimpleStmtAssignable assign;

	public SimpleStmtDeclarationAssignment(SimpleStmtDeclaration declaration, SimpleStmtAssignable assign) {
		this.declaration= declaration;
		this.assign = assign;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO
		return null;
//		
//		List<SemanticError> res = exp.checkSemantics(e);
//		
//		e.addVariable(id, exp.getValue(e));
//		
//		return res;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO
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
