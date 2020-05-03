package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtDeclarationAssignment extends Stmt {
	StmtDeclaration declaration;
	StmtAssignable assign;

	public StmtDeclarationAssignment(StmtDeclaration declaration, StmtAssignable assign) {
		this.declaration = declaration;
		this.assign = assign;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		toRet.addAll(declaration.checkSemantics(e));
		toRet.addAll(assign.checkSemantics(e));
		return toRet;

	}

}
