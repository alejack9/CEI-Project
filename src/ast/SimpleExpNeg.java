package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public class SimpleExpNeg extends SimpleExp {
	
	SimpleExp exp;

	public SimpleExpNeg(SimpleExp exp) {
		this.exp = exp;		
	}

	@Override
	public Descriptor getType(Environment e) {
		return null;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		result.addAll(exp.checkSemantics(e));
			
		return result;
	}

}
