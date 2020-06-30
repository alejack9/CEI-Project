package ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ast.errors.IdAlreadytExistsError;
import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPStmtDecFun extends SPStmtDec {

	private Type type;
	private String ID;
	private List<SPArg> args;
	private SPStmtBlock block;
	
	public SPStmtDecFun(Type type, String ID, List<SPArg> args, SPStmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.args = args;
		this.block = block;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		if (e.containsIDLocal(ID))
			toRet.add(new IdAlreadytExistsError(ID));
		
		e.openScope();
		List<Type> argsT = new LinkedList<Type>();
		int paroffset = 1;
		for (SPArg arg : args) {
	    	  argsT.add(arg.getType());
	    	  
	    	  STEntry toAdd = new STEntry(arg.getType(), e.getNestingLevel(), paroffset++);
	    	  
	    	  if(!e.addID(arg.getId(), toAdd))
	    		  toRet.add(new IdAlreadytExistsError(arg.getId()));
		}
		toRet.addAll(this.block.checkSemantics(e));
		e.closeScope();
		
		ArrowType t = (ArrowType) EType.FUNCTION.getType();
		t.setParamTypes(argsT);
		t.setRetType(type);
		
		e.addID(ID, new STEntry(t, e.getNestingLevel(), e.getOffset()));
		return toRet;
	}


	@Override
	public Type inferType() {
		this.args.stream().map(SPArg::inferType);
		
		Type blockT = this.block.inferType();
		if(!blockT.equals(type))
			throw new TypeError("Block return type (" + blockT + ") not equals to function return type (" + type + ")"); 
		return EType.VOID.getType();
	}
	
	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
}
