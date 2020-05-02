package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

public class SimpleParamDec implements SimpleElementBase{
	 boolean var;
	 SimpleStmtDeclaration dec;

	/**
	 * Creates a delete statement
	 * 
	 * @param id the variable we want to delete
	 */
	public SimpleParamDec(boolean var, SimpleStmtDeclaration dec) {
		this.var = var;
		this.dec = dec;
	}

	/*
	 * Checks if the variable in use exists. if it doesn't then add an error, if it
	 * does then remove it from the current scope
	 */
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = dec.checkSemantics(e);
		
		e.turnIntoParameter(dec.ID, var);
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO
		return null;
//		int cost ;
//		//if the variable exist this will have a cost of -1
//		if(e.containsVariable(id))
//			cost = -1;
//		else cost = 0 ;
//		
//		//put the variable in the current scope with the current value
//		e.deleteVariable(id);
//		
//		return new BTAtom(cost);
	}

}
