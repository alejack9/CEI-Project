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
public class SimpleStmtFunctionDec extends SimpleStmt {
	
	String type;
	String ID;
	List<SimpleParamDec> params;
	SimpleStmtBlock block;
	
	public SimpleStmtFunctionDec(String type,String ID,List<SimpleParamDec> params, SimpleStmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.params = params;
		this.block = block;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		
		List<SemanticError> result = new LinkedList<SemanticError>();
	
		if(e.contains(ID))
			result.add(new SemanticError(Strings.ErrorIDAlreadyExist + ID));
		List<String> paramsType = new LinkedList<String>();
		
		e.openScope();
		params.forEach(param -> {
			result.addAll(param.checkSemantics(e));
			paramsType.add(param.dec.type);
		});
		e.addFunction(ID, type, paramsType);
		result.addAll(block.checkSemantics(e));
		e.closeScope();
	
		return result;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
