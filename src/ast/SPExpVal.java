package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import util_analysis.SemanticError;


public class SPExpVal extends SPExp {

	private int value;

	public SPExpVal(int value) {
		this.value = value;
	}

	// No semantic errors here

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return new LinkedList<SemanticError>();
	}

	@Override
	public int getValue(Environment e) {	
		return value;
	}

	@Override
	public Type inferType() {
		// TODO Auto-generated method stub
		return null;
	}

}
