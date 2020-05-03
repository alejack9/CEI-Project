package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtIfThenElse extends Stmt {

	StmtIfThen ifRule;
	StmtElseRule elseRule;

	public StmtIfThenElse(StmtIfThen ifRule, StmtElseRule elseRule) {
		this.ifRule = ifRule;
		this.elseRule = elseRule;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		toRet.addAll(ifRule.checkSemantics(e));
		if(elseRule != null) toRet.addAll(elseRule.checkSemantics(e));

		return toRet;
	}

}
