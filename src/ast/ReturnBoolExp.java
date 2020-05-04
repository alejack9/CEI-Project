package ast;

import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class ReturnBoolExp extends StmtReturnRule {
	BoolExp exp;

	public ReturnBoolExp(BoolExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
		
	}

}
