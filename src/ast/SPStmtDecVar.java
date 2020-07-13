package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;

public class SPStmtDecVar extends SPStmtDec {

	private Type type;
	private String ID;
	private SPExp exp;
	
	public SPStmtDecVar(Type type, String ID, SPExp exp, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.exp = exp;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		if (!e.add(ID, new STEntry(type, e.getNestingLevel(), e.getOffset())))
			toRet.add(new IdAlreadytExistsError(ID, line, column));

		if(exp != null)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment<BTEntry> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Variable type cannot be void", line, column));
		
		if(exp != null) {
			Type expType = exp.inferType();
			if (!expType.getType().equalsTo(type))
				TypeErrorsStorage.addError(new TypeError("Expression type (" + expType + ") is not equal to variable type (" + type + ")", this.exp.line, this.exp.column));
		}
				
		return EType.VOID.getType();
	}
	
}
