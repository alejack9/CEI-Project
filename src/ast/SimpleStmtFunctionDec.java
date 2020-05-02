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
		
		List<SemanticError> result = new LinkedList<SemanticError>();
	
		if(!e.containsVariable(ID))
			result.add(new SemanticError(Strings.ErrorVariableDoesntExist + ID));
		List<String> paramsType = new LinkedList<String>();
		
		e.openScope();
		params.forEach(param -> {
			result.addAll(param.checkSemantics(e));
			paramsType.add(param.type);
		});
		
		result.addAll(block.checkSemantics(e));

		e.closeScope();
		e.addFunction(ID, type, paramsType);
		return result;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

}
