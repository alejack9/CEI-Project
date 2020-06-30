package ast;

import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpAnd extends SPExpBinBoolBoolIn {

	public SPExpAnd(SPExp left, SPExp right) {
		super(left, right);
	}
	
	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getOp() {
		return "&&";
	}
}
