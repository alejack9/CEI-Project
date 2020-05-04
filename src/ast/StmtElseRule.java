package ast;

import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtElseRule extends Stmt {
	StmtBlock block;

	public StmtElseRule(StmtBlock block) {
		this.block = block;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// NOTE: a new scope will be open 
		return block.checkSemantics(e);
		
	}

}
