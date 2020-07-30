package ast;

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

public class SPStmtRet extends SPStmt {

	private SPExp exp;
	private String k;

	public SPStmtRet(SPExp exp, List<Integer> argsDimension, int line, int column) {
		super(line, column);
		this.exp = exp;
		k = Integer.toString(argsDimension.stream().reduce((a,b) -> a + b).orElse(0) + 64);
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		if(exp != null)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		if(exp != null)
			toRet.addAll(exp.inferBehaviour(e));
		
		return toRet;
	}

	@Override
	public Type inferType() {
		Type toRet = this.exp == null ?
				EType.VOID.getType()
				: exp.inferType();
				
		return toRet;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SPStmtRet");
		// TODO this is horrible
		if(exp != null)
			exp._codeGen(nl, sb);
		sb.newLine(prev, "lw $ra -32($fp)");
		sb.newLine(prev, "addi $sp $sp ", k);
		sb.newLine(prev, "lw $fp 0($sp)");
		sb.newLine(prev, "pop");
		sb.newLine(prev, "jr $ra");
	}
}
