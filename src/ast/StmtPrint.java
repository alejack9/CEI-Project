package ast;

import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

public class StmtPrint extends Stmt {

	private Exp exp;

	public StmtPrint(Exp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return exp.checkSemantics(e);
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return exp.inferBehaviour(e);
	}

	@Override
	public Type inferType() {
		if(EType.VOID.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Cannot print void expression", this.exp.line, this.exp.column));
		return null;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		exp._codeGen(nl, sb);
		sb.newLine("print");
	}

}
