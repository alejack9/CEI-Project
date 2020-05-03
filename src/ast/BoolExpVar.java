package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;
import util_analysis.Environment;

public class BoolExpVar extends BoolExp {

	private String id;

	public BoolExpVar(String id) {
		this.id = id;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariable(id)) 
			toRet.add(new VariableNotExistsError(id));
		return toRet;
	}
	
}
