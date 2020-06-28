package ast;

import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpCall extends SPExp {

	private SPStmtCall call;
	
	public SPExpCall(SPStmtCall call) {
		this.call = call;
	}

	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		return this.call.inferType();
	}

}
