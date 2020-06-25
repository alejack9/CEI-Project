package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.Type;
import util_analysis.Strings;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPExpVar extends SPExp {

	private String id;
	private STEntry idEntry;
	

	public SPExpVar(String id) {
		this.id = id;
	}
	
	// Checks if the variable in use exists. if it doesn't then add an error.
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		

		// TODO Populate idEntry
		
		
		
		List<SemanticError> result = new LinkedList<SemanticError>();
		
//		if(!e.containsVariable(id))
//			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + id));
		
		return result;
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
