package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import ast.errors.SemanticError;


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
		return EType.INT.getType();
	}

}
