package ast;

import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPExpBool extends SPExp {

	boolean value;
	
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
	public SPElementBase inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
