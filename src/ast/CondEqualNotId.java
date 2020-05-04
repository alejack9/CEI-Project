package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;
import util_analysis.Environment;

public class CondEqualNotId extends Cond {

	CondNot leftSide;
	String ID;

	public CondEqualNotId(CondNot leftSide, String id) {
		this.leftSide = leftSide;
		this.ID = id;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		toRet.addAll(leftSide.checkSemantics(e));
		
		//Check if the variable does not exists
		if (!e.containsVariable(ID))
			toRet.add(new VariableNotExistsError(ID));
		return toRet;
	}
}
