package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class StmtAssignableBoolExp extends StmtAssignable {
	BoolExp exp;

	public StmtAssignableBoolExp(BoolExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

}
