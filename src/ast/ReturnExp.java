package ast;

import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class ReturnExp extends StmtReturnRule {
	Exp exp;

	public ReturnExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
		
	}
}
