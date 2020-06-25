package ast;

import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtCall extends SPStmt {

	List<SPExp> exps;
	String ID;

	public SPStmtCall(String ID, List<SPExp> exps) {
		this.ID = ID;
		this.exps = exps;
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

	@Override
	public Type inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
