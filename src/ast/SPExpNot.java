package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

public class SPExpNot extends SPExp {

	private SPExp exp;

	public SPExpNot(SPExp exp, int line, int column) {
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
		if(!EType.BOOL.equalsTo(expT))
			TypeErrorsStorage.addError(new TypeError("Expression type is \"" + expT + "\" but it must be bool.", exp.line, exp.column));
		
		return EType.BOOL.getType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	@Override
	public  void _codeGen(int nl, CustomStringBuilder sb) {
		String one = CodeGenUtils.freshLabel();
		String end = CodeGenUtils.freshLabel();
		
		exp._codeGen(nl, sb);
		sb.newLine("li $t1 0");
		sb.newLine("beq $a0 $t1 ", one);
		sb.newLine("li $a0 0");
		sb.newLine("b ", end);
		sb.newLine(one, ":");
		sb.newLine("li $a0 1");
		sb.newLine(end, ":");	
	}

}
