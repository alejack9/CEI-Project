package ast;

import java.util.List;

import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPStmtCall extends SPStmt {

	private List<SPExp> exps;
	private String ID;
	private STEntry idEntry;

	public SPStmtCall(String ID, List<SPExp> exps) {
		this.ID = ID;
		this.exps = exps;
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

	@Override
	public Type inferType() {
		if(!EType.FUNCTION.equalsTo(idEntry.getType()))
			throw new TypeError("Called ID must be a function (actual type: " + idEntry.getType() + ")");
		
		ArrowType funT = (ArrowType) idEntry.getType();
		
//		// TODO IN CHECKSEMANTICS
//		if(funT.getParamTypes().size() != exps.size()) {}
		
		List<Type> parTs = funT.getParamTypes();
		
		for(int i = 0; i < parTs.size(); i++) {
			Type parT = parTs.get(i);
			Type expT = exps.get(i).inferType(); 
			if(!parT.equals(expT))
				throw new TypeError("#" + i + " parameter type (" + parT + ") is not equal to expression type (" + expT + ")");
		}
		
		return funT.getReturnType();
	}

}
