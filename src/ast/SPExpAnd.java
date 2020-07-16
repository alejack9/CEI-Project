package ast;

import java.util.List;

import ast.errors.BehaviourError;
import util_analysis.Environment;

public class SPExpAnd extends SPExpBinBoolBoolIn {

	public SPExpAnd(SPExp left, SPExp right, int line, int column) {
		super(left, right, line, column);
	}

	@Override
	protected String getOp() {
		return "&&";
	}
}
