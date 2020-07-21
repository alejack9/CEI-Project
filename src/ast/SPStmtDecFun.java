package ast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.SemanticError;

public class SPStmtDecFun extends SPStmtDec {

	private Type type;
	private String ID;
	private List<SPArg> args;
	private SPStmtBlock block;
	
	public SPStmtDecFun(Type type, String ID, List<SPArg> args, SPStmtBlock block, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.args = args;
		this.block = block;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		Environment<STEntry> funEnv = new ListOfMapEnv<STEntry>(e.getAllFunctions());
		
		funEnv.openScope();
		List<Type> argsT = new LinkedList<Type>();
		int paroffset = 1;
		for (SPArg arg : args) {
	    	  argsT.add(arg.getType());
	    	  
	    	  if(!funEnv.add(arg.getId(), new STEntry(arg.getType(), funEnv.getNestingLevel(), paroffset++)))
	    		  toRet.add(new IdAlreadytExistsError(arg.getId(), arg.line, arg.column));
		}
		
		toRet.addAll(block.checkSemantics(funEnv));
		funEnv.closeScope();
	
		ArrowType t = (ArrowType) EType.FUNCTION.getType();
		t.setParamTypes(argsT);
		t.setRetType(type);
		
		if (!e.add(ID, new STEntry(t, e.getNestingLevel(), e.getOffset())))
			toRet.add(new IdAlreadytExistsError(ID, line, column));
		
		return toRet;
	}

	@Override
	public Type inferType() {
		this.args.stream().map(SPArg::inferType);
		
		Type blockT = this.block.inferType();
		if(!blockT.getType().equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Block return type (" + blockT + ") not equals to function return type (" + type + ")", this.block.line, this.block.column)); 
		return EType.VOID.getType();
	}
	
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		Environment<BTEntry> funEnv = new ListOfMapEnv<BTEntry>(e.getAllFunctions());
		
		List<BTEntry> e0 = new LinkedList<BTEntry>();
		args.stream().forEach(arg -> e0.add(new BTEntry()));
		List<BTEntry> e1 = e0;
		List<BTEntry> e1_1 = e0;
		
		// e1 = e1_1 = tutti params a bottom
		do {
			funEnv.openScope();
			for(int i = 0; i < e0.size(); i++)
				funEnv.add(args.get(i).getId(), (BTEntry) e0.get(i).clone());
			toRet.addAll(block.inferBehaviour(funEnv));
			e1 = e1_1;
			for(int i = 0; i < e0.size(); i++)
				e1_1.set(i, funEnv.getIDEntry(args.get(i).getId()));
			funEnv.closeScope();
			funEnv.update(ID, new BTEntry(e1_1));
		} while(!BTEntry.areEqual(e1, e1_1));
		/**
		 * int f(int var x) {  int y; }
		 */
		
		return toRet;
	}
}
