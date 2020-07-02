package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import ast.errors.SemanticError;


public class SPExpVal extends SPExp {

	private int value;

	public SPExpVal(int value, int line, int column) {
		super(line, column);
		this.value = value;
	}

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
