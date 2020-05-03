package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import ast.exceptions.VariableNotExistsError;
import util_analysis.Environment;

public class CondEqualNotId extends Cond {

	CondNot leftSide;
	String ID;

	public CondEqualNotId(CondNot leftSide, String id) {
		this.leftSide = leftSide;
		this.ID = id;
	}

	@Override
	public Descriptor getType(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		toRet.addAll(leftSide.checkSemantics(e));	
		if (!e.containsVariable(ID))
			toRet.add(new VariableNotExistsError(ID));
		return toRet;
	}
}
