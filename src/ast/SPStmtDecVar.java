package ast;

import java.util.Collections;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;

public class SPStmtDecVar extends SPStmtDec {

	private Type type;
	private String ID;
	private SPExp exp;
	
	public SPStmtDecVar(Type type, String ID, SPExp exp) {
		this.type = type;
		this.ID = ID;
		this.exp = exp;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = Collections.emptyList();
		
		// add or replace ID (inferBehavior checks that the ID has been deleted before the second declaration)
		e.addID(ID, new STEntry(type, e.getNestingLevel(), e.getOffset()));

		if(exp != null)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			throw new TypeError("Variable type cannot be void");
		
		Type expType = exp.inferType();
		if (this.exp != null && !expType.getType().equalsTo(type))
			throw new TypeError("Expression type (" + expType + ") is not equal to variable type (" + type + ")");
		
		return EType.VOID.getType();
	}
	
}
