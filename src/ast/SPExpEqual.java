package ast;

import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpEqual extends SPExpBinBoolAllIn {

	public SPExpEqual(SPExp left, SPExp right) {
		super(left,right);
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
	protected String getOp() {
		return "==";
	}
}
