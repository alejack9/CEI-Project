package ast;

import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPExpCall extends SPExp {

	SPStmtCall call;
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
