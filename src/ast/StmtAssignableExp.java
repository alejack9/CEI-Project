package ast;

import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtAssignableExp extends StmtAssignable {
	Exp exp;

	public StmtAssignableExp(Exp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

}
