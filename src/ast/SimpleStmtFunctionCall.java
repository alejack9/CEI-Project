package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
