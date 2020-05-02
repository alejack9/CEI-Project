package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleCondNot extends SimpleCond {

	private SimpleBoolExp exp;

	public SimpleCondNot(SimpleBoolExp exp) {
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

	@Override
	public STEntry getType(Environment e) {
		return null;
	}

}
