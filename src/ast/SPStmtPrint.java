package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import ast.errors.SemanticError;

public class SPStmtPrint extends SPStmt {

	private SPExp exp;

	public SPStmtPrint(SPExp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return exp.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment<BTEntry> e) {
		
		return new BTPrint(exp.getValue(e));
	}

	@Override
	public Type inferType() {
		if(EType.VOID.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Cannot print void expression", this.exp.line, this.exp.column));
		return EType.VOID.getType();
	}

}
