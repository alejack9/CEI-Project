package ast;

import java.util.List;

import ast.exceptions.SemanticError;
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

		e.turnIntoParameter(dec.ID, var);

		return toRet;
	}

}
