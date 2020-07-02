package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import ast.errors.SemanticError;

public class SPStmtIte extends SPStmt {

	private SPExp exp;
	private SPStmt thenStmt, elseStmt;

	public SPStmtIte(SPExp exp, SPStmt thenStmt, SPStmt elseStmt, int line, int column) {
		super(line, column);
		this.exp = exp;
		this.thenStmt = thenStmt;
		this.elseStmt = elseStmt;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(exp.checkSemantics(e));
		toRet.addAll(thenStmt.checkSemantics(e));
		
		if(elseStmt != null)
			toRet.addAll(elseStmt.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if(!EType.BOOL.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Condition must be bool type", this.exp.line, this.exp.column));
		
		Type thenT = this.thenStmt.inferType();
		
		if(this.elseStmt != null) {
			Type elseT = this.elseStmt.inferType();
			if(!elseT.getType().equalsTo(thenT))
				TypeErrorsStorage.addError(new TypeError("Then branch (" + thenT + ") does not return the same type of else branch (" + elseT + ")", this.thenStmt.line, this.thenStmt.column));
		}
		return thenT;
	}

}
