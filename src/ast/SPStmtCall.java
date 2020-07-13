package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import ast.errors.BehaviourError;
import ast.errors.FunctionNotExistsError;
import ast.errors.ParametersMismatchError;
import ast.errors.PassedReferenceNotVarError;
import ast.errors.SemanticError;

public class SPStmtCall extends SPStmt {

	private List<SPExp> exps;
	private String ID;
	private STEntry idEntry;

	public SPStmtCall(String ID, List<SPExp> exps, int line, int column) {
		super(line, column);
		this.ID = ID;
		this.exps = exps;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		idEntry = e.getIDEntry(ID);
		
		Type funT = idEntry.getType();
		
		if(idEntry == null || !EType.FUNCTION.equalsTo(funT))
			toRet.add(new FunctionNotExistsError(ID, line, column));
		else {
			List<Type> params = ((ArrowType) funT).getParamTypes();
			if(exps.size() != params.size())
				toRet.add(new ParametersMismatchError(params.size(), exps.size(), line, column));
			for(int i = 0; i < Math.min(exps.size(), params.size()); i++) {
				if(params.get(i).IsRef() && !(exps.get(i) instanceof SPExpVar)) {
					toRet.add(new PassedReferenceNotVarError(i+1, ID, exps.get(i).line, exps.get(i).column));
				}
			}
		}
		
		for (SPExp exp : exps)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type inferType() {
		if(!EType.FUNCTION.equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Called ID must be a function (actual type: " + idEntry.getType() + ")", line, column));
		
		ArrowType funT = (ArrowType) idEntry.getType();
		
		List<Type> parTs = funT.getParamTypes();
		
		for(int i = 0; i < parTs.size(); i++) {
			Type parT = parTs.get(i);
			Type expT = exps.get(i).inferType(); 
			if(!parT.equals(expT))
				TypeErrorsStorage.addError(new TypeError("#" + (i + 1) + " parameter type (" + parT + ") is not equal to expression type (" + expT + ")", line, column));
		}
		
		return funT.getReturnType();
	}

}
