package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

public abstract class SPExpBin extends SPExp {
	
	protected SPExp leftSide, rightSide;

	protected abstract String getOp();
	
	protected SPExpBin(SPExp left, SPExp right, int line, int column) {
		super(line, column);
		this.leftSide = left;
		this.rightSide = right;
	}
	
	@Override
	public final List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(leftSide.checkSemantics(e));
		toRet.addAll(rightSide.checkSemantics(e));
			
		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		toRet.addAll(leftSide.inferBehaviour(e));
		toRet.addAll(rightSide.inferBehaviour(e));
		return toRet;
	}
}
