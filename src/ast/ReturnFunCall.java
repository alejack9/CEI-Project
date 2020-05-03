package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class ReturnFunCall extends StmtReturnRule {
	StmtFunctionCall fun;

	public ReturnFunCall(StmtFunctionCall fun) {
		this.fun = fun;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return fun.checkSemantics(e);	
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
