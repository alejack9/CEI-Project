package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtIte extends SPStmt {

	SPExp exp;
	SPStmt thenStmt, elseStmt;

	public SPStmtIte(SPExp exp, SPStmt thenStmt, SPStmt elseStmt) {
		this.exp = exp;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if(!EType.BOOL.equalsTo(exp.inferType()))
			throw new TypeError("Condition must be bool type");
		
		Type thenT = this.thenStmt.inferType();
		
		if(this.elseStmt != null) {
			Type elseT = this.elseStmt.inferType();
			if(!elseT.getType().equalsTo(thenT))
				throw new TypeError("Then branch (" + thenT + ") does not return the same type of else branch (" + elseT + ")");
		}
		return thenT;
	}

}
