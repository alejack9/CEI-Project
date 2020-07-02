package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import util_analysis.Environment;
import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;

public class SPExpVar extends SPExp {

	private String id;
	private STEntry idEntry;
	

	public SPExpVar(String id, int line, int column) {
		super(line, column);
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		idEntry = e.getIDEntry(id);
		
		if(idEntry == null) 
			toRet.add(new VariableNotExistsError(id, line, column));
		
		return toRet;
	}

	@Override
	public int getValue(Environment e) {	
		return 0;
//		return e.getIDEntry(id);
	}

	@Override
	public Type inferType() {		
		return this.idEntry.getType();
	}

}
