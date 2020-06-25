package ast;

import java.util.List;
import java.util.stream.Collectors;


import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtDecFun extends SPStmtDec {

	String type;
	String ID;
	List<SPArg> args;
	SPStmtBlock block;
	
	public SPStmtDecFun(String type, String ID, List<SPArg> args, SPStmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.args = args;
		this.block = block;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		if (e.containsIDLocal(ID)) throw new SemanticError("ID already used in current scope");
		
		e.openScope();
		// environment is modified in each iteration because it's passed by reference
		List<Type> typeArgs = this.args.stream().map(SPArg::inferType).collect(Collectors.toList());
		this.block.inferType();
		e.closeScope();
		
		ArrowType t = (ArrowType) EType.FUNCTION.getType(); 
		t.setParamTypes(typeArgs);
		e.addID(ID, new STEntry(t, e.getNestingLevel(), 0));
		return null;
	}


	@Override
	public Type inferType() {
		this.args.stream().map(SPArg::inferType);
		
		Type t = this.block.inferType();
		if (!t.getType().equals(EType.valueOf(this.type.toUpperCase())))
			throw new TypeError("Block return type (" + t + ") not equals to function return type (" + this.type + ")"); 
		return EType.VOID.getType();
	}
	
	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
}
