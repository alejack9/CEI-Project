package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import util_analysis.Environment;
import util_analysis.EnvironmentFun;
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
		
		e.openScope();
		List<Type> argsT = new LinkedList<Type>();
		int paroffset = 1;
		for (SPArg arg : args) {
	    	  argsT.add(arg.getType());
	    	  
	    	  if(!e.add(arg.getId(), new STEntry(arg.getType(), e.getNestingLevel(), paroffset++)))
	    		  toRet.add(new IdAlreadytExistsError(arg.getId(), arg.line, arg.column));
		}
		
		toRet.addAll(block.checkSemantics(new EnvironmentFun<STEntry>(e)));
		e.closeScope();
	
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
		if(!blockT.equals(type))
			TypeErrorsStorage.addError(new TypeError("Block return type (" + blockT + ") not equals to function return type (" + type + ")", this.block.line, this.block.column)); 
		return EType.VOID.getType();
	}
	
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		Environment<BTEntry> eFun = new EnvironmentFun<BTEntry>(e);
		
		Environment<BTEntry> e0 = new ListOfMapEnv<BTEntry>();
		args.stream().forEach(arg -> e0.add(arg.getId(), new BTEntry()));
		e0.add(ID, new BTEntry(e0, e0));
		Environment<BTEntry> e1 = e0;
		Environment<BTEntry> e1_1 = e0;
		
		e.addScope(e0.getCurrentScope());
		do {
			toRet.addAll(block.inferBehaviour(e));
			e1 = e1_1;
			e1_1 = e.getCurrentScope();
			e.update(ID, new BTEntry(e0, e1_1));
		} while(e1 != e1_1);
		e.closeScope();
		
		
		
//		e.openScope();
//		args.stream().forEach(arg -> e.add(arg.getId(), new BTEntry()));
//		
//		e.add(ID, new BTEntry(e0, e1));
//		
//		do {
//			block.inferBehaviour(e);
//			e1 = e11;
//			e11 = e.getCurrentScope();
//			e.update(ID, new BTEntry(e0, e11));
//		} while(e1 != e11);
//		e.closeScope();
		
		return toRet;
	}
}
