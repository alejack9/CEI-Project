package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleStmtIfThen extends SimpleStmt {
	SimpleBoolExp exp;
	SimpleStmtBlock block;

	public SimpleStmtIfThen(SimpleBoolExp exp, SimpleStmtBlock block) {
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

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
