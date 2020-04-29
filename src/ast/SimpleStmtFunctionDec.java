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
public class SimpleStmtFunctionDec extends SimpleStmt {
	
	String type;
	String ID;
	List<SimpleStmtDeclaration> params;
	SimpleStmtBlock block;
	
	public SimpleStmtFunctionDec(String type,String ID,List<SimpleStmtDeclaration> params, SimpleStmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.params = params;
		this.block = block;
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
