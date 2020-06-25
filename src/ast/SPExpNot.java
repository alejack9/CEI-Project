package ast;

import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPExpNot extends SPExp {

	SPExp exp;

	public SPExpNot(SPExp exp) {
		this.exp = exp;
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
	public Type inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
