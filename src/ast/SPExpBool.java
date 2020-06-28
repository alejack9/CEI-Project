package ast;

import java.util.List;

import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpBool extends SPExp {

	private boolean value;
	
	public SPExpBool(boolean value) {
		this.value = value;
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
		return EType.BOOL.getType();
	}

}
