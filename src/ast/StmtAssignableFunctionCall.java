package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class StmtAssignableFunctionCall extends StmtAssignable {
	StmtFunctionCall fun;

	public StmtAssignableFunctionCall(StmtFunctionCall fun) {
		this.fun = fun;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return fun.checkSemantics(e);		
	}

}
