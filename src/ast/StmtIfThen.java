package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtIfThen extends Stmt {
	BoolExp exp;
	StmtBlock block;

	public StmtIfThen(BoolExp exp, StmtBlock block) {
		this.exp = exp;
		this.block = block;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		toRet.addAll(block.checkSemantics(e));
		toRet.addAll(exp.checkSemantics(e));

		return toRet;
	}
}
