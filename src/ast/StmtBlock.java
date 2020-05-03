package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;

import util_analysis.Environment;

public class StmtBlock extends Stmt {

	List<Stmt> children;

	public StmtBlock(List<Stmt> children) {
		this.children = children;
	}

	public List<SemanticError> checkSemantics(Environment e) {

		e.openScope();

		LinkedList<SemanticError> result = new LinkedList<SemanticError>();

		if (children != null)
			for (Stmt el : children)
				result.addAll(el.checkSemantics(e));

		e.closeScope();

		return result;
	}

}
