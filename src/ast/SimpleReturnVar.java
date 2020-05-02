package ast;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.BTAtom;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

public class SimpleReturnVar extends SimpleStmtReturnRule {
	String ID;

	public SimpleReturnVar(String ID) {
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();
		
		if(!e.containsVariable(ID))
			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + ID));
		
		return result;
		
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO unimplemented
		return null;
	}

}
