package ast;

import java.util.List;

import ast.exceptions.SemanticError;
import util_analysis.Environment;

public interface ElementBase {

	public abstract List<SemanticError> checkSemantics(Environment e);
	
}
