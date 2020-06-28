package ast;

import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpGreaterThan extends SPExpBinBoolIntIn {

	public SPExpGreaterThan(SPExp leftSide, SPExp rightSide) {
		super(leftSide, rightSide);
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
		return ">";
	}
}
