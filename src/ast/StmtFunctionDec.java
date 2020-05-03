package ast;

import java.util.LinkedList;
import java.util.List;

import ast.exceptions.IdAlreadytExistsError;
import ast.exceptions.SemanticError;
import behavioural_analysis.BTBase;
import util_analysis.Environment;

/**
 * Represents a Simple Expression Some child classes of this one will be
 * SimpleExpSum, SimpleExpDiff, SimpleExpDiv, SimpleExpMult and SimpleExpNeg
 * 
 * @author Abel
 *
 */
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

		e.addFunction(ID, type);
		e.openScope();
		e.addFunction(ID, type);
		params.forEach(param -> result.addAll(param.checkSemantics(e)));
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
