package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.errors.VariableNotExistsError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import ast.errors.SemanticError;

public class SPStmtAssignment extends SPStmt{
	
	private String id;
	private STEntry idEntry;
	private SPExp exp;

	public SPStmtAssignment(String id, SPExp exp, int line, int column) {
		super(line, column);
		this.id = id;
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(id);
		if(idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));
				
		toRet.addAll(exp.checkSemantics(e));

		return toRet;		
	}

	@Override
	public BTBase inferBehavior(Environment<BTEntry> e) {
		int cost ;
		//if the variable doesn't exist in the current scope then 
		//it has a cost equals to 1
		if(e.getLocalIDEntry(id) == null)
			cost = 1;
		else cost = 0 ;
		
		//put the variable in the current scope with the current value
		// e.addVariable(id, exp.getValue(e));
		
		return new BTAtom(cost);
	}

	@Override
	public Type inferType() {
		Type expType = exp.inferType(); 
		if(!expType.getType().equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Variable type (" + idEntry.getType() + ") is not equal to expression type (" + expType +")", line, column));
		
		return EType.VOID.getType();
	}

}
