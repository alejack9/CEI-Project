package ast;

import java.util.List;

import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPStmtRet extends SPStmt {

	private SPExp exp;

	public SPStmtRet(SPExp exp) {
		this.exp = exp;
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
		Type toRet = this.exp == null ?
				EType.VOID.getType()
				: exp.inferType();
				
		return toRet;
	}
}
