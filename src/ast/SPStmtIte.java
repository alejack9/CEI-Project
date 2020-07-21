package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		toRet.addAll(exp.checkSemantics(e));
		
		if(elseStmt != null) {
			Environment<STEntry> tempE = (Environment<STEntry>) e.clone();
			
			toRet.addAll(elseStmt.checkSemantics(tempE));
		}
		
		toRet.addAll(thenStmt.checkSemantics((Environment<STEntry>) e.clone()));
		
		return toRet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		toRet.addAll(exp.inferBehaviour(e));
		
		Environment<BTEntry> tempE = null;
		
		if (elseStmt != null) {
			tempE = (Environment<BTEntry>) e.clone();
			toRet.addAll(elseStmt.inferBehaviour(tempE));
		}
		
		toRet.addAll(thenStmt.inferBehaviour(e));
		
		if(tempE != null)
			BTHelper.maxModifyEnv(e, tempE);
			
		return toRet;
	}

	@Override
	public Type inferType() {
		if(!EType.BOOL.equalsTo(exp.inferType()))
			TypeErrorsStorage.addError(new TypeError("Condition must be bool type", exp.line, exp.column));
		
		Type thenT = this.thenStmt.inferType();
		
		if(this.elseStmt != null) {
			Type elseT = this.elseStmt.inferType();
			if(!elseT.getType().equalsTo(thenT))
				TypeErrorsStorage.addError(new TypeError("Then branch (" + thenT + ") does not return the same type of else branch (" + elseT + ")", this.thenStmt.line, this.thenStmt.column));
		}
		return thenT;
	}
}
