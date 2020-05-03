package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;

import util_analysis.Environment;

public class StmtAssignableVar extends StmtAssignable {
	String ID;

	public StmtAssignableVar(String ID) {
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariable(ID))
			toRet.add(new VariableNotExistsError(ID));
		return toRet;
	}
}
