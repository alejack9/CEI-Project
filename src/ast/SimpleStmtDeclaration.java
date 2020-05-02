package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableAlreadyExistsError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleStmtDeclaration extends SimpleStmt {

	String type, ID;
	
	public SimpleStmtDeclaration(String type, String ID) {
		this.type = type;
		this.ID = ID;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariableLocal(ID))
			e.addVariable(ID, type);
		else
			toRet.add(new VariableAlreadyExistsError(ID));
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
