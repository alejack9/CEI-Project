package ast;

import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtIte extends SPStmt {

	SPExp exp;
	SPStmt thenStmt, elseStmt;

	public SPStmtIte(SPExp exp, SPStmt thenStmt, SPStmt elseStmt) {
		this.exp = exp;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
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
	public Type inferType() {
		// TODO Auto-generated method stub
		return null;
	}

}
