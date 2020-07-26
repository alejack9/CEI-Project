package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

public class SPExpNeg extends SPExp {
	
	private SPExp exp;

	public SPExpNeg(SPExp exp, int line, int column) {
		super(line, column);
		this.exp = exp;		
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(exp.checkSemantics(e));
			
		return toRet;
	}

	@Override
	public Type inferType() {
		Type expT = this.exp.inferType();
		if(!EType.INT.equalsTo(expT))
			TypeErrorsStorage.addError(new TypeError("Expression type is \"" + expT + "\" but it must be int.", exp.line, exp.column));
		
		return EType.INT.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

//	@Override
	public String codeGen(int nl) {
		StringBuilder sb = new StringBuilder();
//		sb.append(exp.codeGen(nl));
		sb.append("\r\n");
		sb.append("neg $a0");
		return sb.toString();
	}
}
