package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;


public class SPExpVal extends SPExp {

	private int value;

	public SPExpVal(int value, int line, int column) {
		super(line, column);
		this.value = value;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return new LinkedList<SemanticError>();
	}

	@Override
	public Type inferType() {
		return EType.INT.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return Collections.emptyList();
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		sb.newLine("li $a0 ", Integer.toString(value));
	}

}
