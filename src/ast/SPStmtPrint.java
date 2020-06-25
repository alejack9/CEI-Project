package ast;

import java.util.List;

import ast.types.Type;
import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtPrint extends SPStmt {

	private SPExp exp;

	public SPStmtPrint(SPExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		
		return new BTPrint(exp.getValue(e));
	}

	@Override
	public Type inferType() {
		// TODO Auto-generated method stub
		return null;
	}

}
