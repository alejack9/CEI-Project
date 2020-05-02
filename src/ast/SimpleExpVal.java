package ast;

import java.util.LinkedList;
import java.util.List;

import util_analysis.Environment;
import util_analysis.SemanticError;


public class SimpleExpVal extends SimpleExp {

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
	public STEntry getType(Environment e) {	
		return null;
	}

}
