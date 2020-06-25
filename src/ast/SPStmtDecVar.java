package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtDecVar extends SPStmtDec {

	String type;
	String ID;
	SPExp exp;
	
	public SPStmtDecVar(String type, String ID, SPExp exp) {
		this.type = type;
		this.ID = ID;
		this.exp = exp;
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
		if (EType.VOID.equalsTo(this.type))
			throw new TypeError("Variable type cannot be void");
		
		Type expType = this.exp.inferType();
		if (this.exp != null && !expType.getType().equalsTo(this.type))
			throw new TypeError("Expression type (" + expType + ") is not equal to variable type (" + this.type + ")");
		
		return EType.VOID.getType();
	}
	
}
