package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class ExpVal extends Exp {

	@SuppressWarnings("unused")
	private int value;

	public ExpVal(int value) {
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return new LinkedList<SemanticError>();
	}

}
