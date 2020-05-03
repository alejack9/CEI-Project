package ast;

import java.util.List;

import ast.exceptions.SemanticError;

import util_analysis.Environment;

public class StmtPrint extends Stmt {

	private Exp exp;

	public StmtPrint(Exp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

}
