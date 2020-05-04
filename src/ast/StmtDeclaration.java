package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;
import ast.errors.VariableAlreadyExistsError;
import util_analysis.Environment;

public class StmtDeclaration extends Stmt {

	String type, ID;

	public StmtDeclaration(String type, String ID) {
		this.type = type;
		this.ID = ID;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		//Check if the variable does not exists in local scope and add it, return error otherwise
		if (!e.containsLocal(ID))
			e.addVariable(ID, type);
		else
			toRet.add(new IdAlreadytExistsError(ID));
		return toRet;
	}

}
