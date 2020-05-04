package ast;

import java.util.List;

import ast.errors.SemanticError;
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

}
