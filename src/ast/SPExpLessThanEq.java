package ast;

import java.util.List;

import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPExpLessThanEq extends SPExpBinArithm {
	
	public SPExpLessThanEq(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	public int getValue(Environment e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected String getOp() {
		return "<=";
	}
}
