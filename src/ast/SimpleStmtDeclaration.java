package ast;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

public class SimpleStmtDeclaration extends SimpleStmt {

	String type, ID;
	
	public SimpleStmtDeclaration(String type, String ID) {
		this.type = type;
		this.ID = ID;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (!e.containsVariableLocal(ID))
			e.addVariable(ID, type);
		else
			toRet.add(new SemanticError(Strings.ErrorVariableAlreadyExist + this.ID));
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
