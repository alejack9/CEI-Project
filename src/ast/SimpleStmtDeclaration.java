package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtDeclaration extends SimpleStmt {

	String type, ID;
	
	public SimpleStmtDeclaration(String type, String ID) {
		this.type = type;
		this.ID = ID;
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
