package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;
import util_analysis.Environment;

public class ReturnVar extends StmtReturnRule {
	String ID;

	public ReturnVar(String ID) {
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();

		//Check if the variable does not exists
		if (!e.containsVariable(ID))
			result.add(new VariableNotExistsError(ID));

		return result;

	}
}
