package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.FunctionNotExistsError;
import ast.errors.SemanticError;
import util_analysis.Environment;

public class StmtFunctionCall extends Stmt {
	String ID;
	List<ParamDef> params;

	public StmtFunctionCall(String ID, List<ParamDef> params) {
		this.ID = ID;
		this.params = params;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		if (!e.containsFunction(ID))
			toRet.add(new FunctionNotExistsError(ID));

		params.forEach(param -> toRet.addAll(param.checkSemantics(e)));

		return toRet;
	}
}
