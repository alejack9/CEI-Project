package ast;

import java.util.LinkedList;
import java.util.List;

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
	public SPElementBase inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
