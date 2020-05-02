package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

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
	public Descriptor getType(Environment e) {
		return null;
	}

}
