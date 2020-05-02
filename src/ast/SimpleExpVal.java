package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;


public class SimpleExpVal extends SimpleExp {

	@SuppressWarnings("unused")
	private int value;

	public SimpleExpVal(int value) {
		this.value = value;
	}

	// No semantic errors here

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return new LinkedList<SemanticError>();
	}

	@Override
	public Descriptor getType(Environment e) {	
		return null;
	}

}
