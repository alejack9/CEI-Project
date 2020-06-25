package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import util_analysis.SemanticError;
import util_analysis.Environment;

public class SPExpNeg extends SPExp {
	
	SPExp exp;

	public SPExpNeg(SPExp exp) {
		this.exp = exp;		
	}

	@Override
	public int getValue(Environment e) {
		return -exp.getValue(e);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		result.addAll(exp.checkSemantics(e));
			
		return result;
	}

	@Override
	public Type inferType() {
		Type expT = this.exp.inferType();
		if(!EType.INT.equalsTo(expT))
			throw new TypeError("Expression type is \"" + expT + "\" but it must be int.");
		
		return EType.INT.getType();
	}

}
