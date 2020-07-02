package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import behavioural_analysis.BTPrint;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPStmtPrint extends SPStmt {

	private SPExp exp;

	public SPStmtPrint(SPExp exp, int line, int column) {
		super(line, column);
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		return exp.checkSemantics(e);
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		
		return new BTPrint(exp.getValue(e));
	}

	@Override
	public Type inferType() {
		if(EType.VOID.equalsTo(exp.inferType()))
			throw new TypeError("Cannot print void expression", line, column);
		return EType.VOID.getType();
	}

}
