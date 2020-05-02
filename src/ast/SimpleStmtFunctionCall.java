package ast;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;
import util_analysis.Strings;

/**
 * Represents a Simple Expression
 * Some child classes of this one will be SimpleExpSum, SimpleExpDiff, 
 * SimpleExpDiv, SimpleExpMult and SimpleExpNeg
 * @author Abel
 *
 */
public class SimpleStmtFunctionCall extends SimpleStmt {
	String ID;
	List<SimpleParamDef> params;
	
	public SimpleStmtFunctionCall(String ID,List<SimpleParamDef> params) {
		this.ID = ID;
		this.params = params;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		if (!e.containsVariable(ID))
			toRet.add(new SemanticError(Strings.ErrorVariableDoesntExist));
		
		params.forEach(param -> toRet.addAll(param.checkSemantics(e)) );
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
