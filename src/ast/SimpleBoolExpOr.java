package ast;

import java.util.List;

import util_analysis.SemanticError;
import util_analysis.Environment;

public class SimpleBoolExpOr extends SimpleBoolExp {
	SimpleBoolExp leftSide;
	SimpleBoolExp rightSide;

	public SimpleBoolExpOr(SimpleBoolExp leftSide, SimpleBoolExp rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public boolean getValue(Environment e) {
		throw new Error("Method not implemented");
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		throw new Error("Method not implemented");
//		List<SemanticError> result = new LinkedList<SemanticError>();
//		
//		result.addAll(exp.checkSemantics(e));
//			
//		return result;
	}

}
