package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

public class SimpleCondEqualNotId extends SimpleCond {

	SimpleCondNot leftSide;
	String ID;

	public SimpleCondEqualNotId(SimpleCondNot leftSide, String id) {
		this.leftSide = leftSide;
		this.ID = id;
	}

	@Override
	public STEntry getType(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		toRet.addAll(leftSide.checkSemantics(e));	
		if (!e.containsVariable(ID))
			toRet.add(new SemanticError(Strings.ErrorVariableDoesntExist));
		return toRet;
	}
}
