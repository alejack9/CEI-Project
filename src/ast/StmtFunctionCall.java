package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.FunctionNotExistsError;
import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

/**
 * Represents a Simple Expression
 * Some child classes of this one will be SimpleExpSum, SimpleExpDiff, 
 * SimpleExpDiv, SimpleExpMult and SimpleExpNeg
 * @author Abel
 *
 */
public class StmtFunctionCall extends Stmt {
	String ID;
	List<ParamDef> params;
	
	public StmtFunctionCall(String ID,List<ParamDef> params) {
		this.ID = ID;
		this.params = params;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		if (!e.containsFunction(ID))
			toRet.add(new FunctionNotExistsError(ID));

		params.forEach(param -> toRet.addAll(param.checkSemantics(e)) );
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
