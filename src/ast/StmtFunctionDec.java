package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtFunctionDec extends Stmt {

	String type;
	String ID;
	List<ParamDec> params;
	StmtBlock block;

	public StmtFunctionDec(String type, String ID, List<ParamDec> params, StmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.params = params;
		this.block = block;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {

		List<SemanticError> result = new LinkedList<SemanticError>();

		if (e.contains(ID))
			result.add(new IdAlreadytExistsError(ID));
		
		// the function is added to the scope
		e.addFunction(ID, type);
		
		e.openScope();
		// the function is added also here to return an error if a parameter has the same id of the function
		e.addFunction(ID, type);
		params.forEach(param -> result.addAll(param.checkSemantics(e)));
		// a new scope will be open
		result.addAll(block.checkSemantics(e));
		// all the additions will be deleted 
		e.closeScope();

		return result;
	}

}
