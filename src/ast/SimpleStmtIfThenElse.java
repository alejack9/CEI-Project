package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleStmtIfThenElse extends SimpleStmt {

	SimpleStmtIfThen ifRule;
	SimpleStmtElseRule elseRule;
	
	public SimpleStmtIfThenElse(SimpleStmtIfThen ifRule, SimpleStmtElseRule elseRule) {
		this.ifRule = ifRule;
		this.elseRule = elseRule;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(ifRule.checkSemantics(e));
		toRet.addAll(elseRule.checkSemantics(e));	
	
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
}
