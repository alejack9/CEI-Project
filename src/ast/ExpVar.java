package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import util_analysis.Environment;

public class ExpVar extends Exp {

	private String id;

	public ExpVar(String id) {
		this.id = id;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();

		if (!e.containsVariable(id))
			result.add(new VariableNotExistsError(id));

		return result;
	}

}
