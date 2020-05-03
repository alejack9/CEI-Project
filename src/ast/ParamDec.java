package ast;

import java.util.List;

import ast.errors.SemanticError;
import util_analysis.Environment;

public class ParamDec implements ElementBase {
	boolean var;
	StmtDeclaration dec;

	
	public ParamDec(boolean var, StmtDeclaration dec) {
		this.var = var;
		this.dec = dec;
	}

	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = dec.checkSemantics(e);
		
		//Variable declared as parameter
		e.turnIntoParameter(dec.ID, var);

		return toRet;
	}

}
