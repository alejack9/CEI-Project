package ast;

import java.util.LinkedList;
import java.util.List;

import ast.errors.BehaviourError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.TypeError;
import ast.types.ArrowType;
import ast.types.EType;
import ast.types.Type;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.SemanticError;

public class StmtDecFun extends StmtDec {

	private Type type;
	private String ID;
	private List<Arg> args;
	private StmtBlock block;
	private STEntry idEntry;
	
	public StmtDecFun(Type type, String ID, List<Arg> args, StmtBlock block, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.args = args;
		this.block = block;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		ArrowType t = (ArrowType) EType.FUNCTION.getType();
		
		idEntry = new STEntry(t);
		if (!e.add(ID, idEntry))
			toRet.add(new IdAlreadytExistsError(ID, line, column));
		
		Environment<STEntry> funEnv = new ListOfMapEnv<STEntry>(e.getAllFunctions());
		funEnv.setOffset(e.getOffset());
		
		funEnv.openScope();
		funEnv.increaseNestingLevel();
		List<Type> argsT = new LinkedList<Type>();
		int paroffset = 4; // access link dimension
		int oldEnvOffset = funEnv.getOffset();
		for(int i = 0; i < args.size(); i++) {
			STEntry toAdd = new STEntry(args.get(i).getType());
	    	  argsT.add(toAdd.getType());
	    	  if(!funEnv.add(args.get(i).getId(), toAdd))
	    		  toRet.add(new IdAlreadytExistsError(args.get(i).getId(), args.get(i).line, args.get(i).column));
	    	  else
	    		  toAdd.setOffset(paroffset);
	    	  paroffset += args.get(i).getType().getDimension();
		}
		
		t.setParamTypes(argsT);
		t.setRetType(type);
		funEnv.setOffset(oldEnvOffset);
		
		toRet.addAll(block.checkSemanticsSameScope(funEnv));

		funEnv.closeScope();
		funEnv.decreaseNestingLevel();
		funEnv.setOffset(oldEnvOffset);
		return toRet;
	}

	@Override
	public Type inferType() {
		this.args.forEach(Arg::inferType);
		
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
		List<BTEntry> e1 = new LinkedList<BTEntry>();
		args.stream().forEach(arg -> e1.add(new BTEntry()));
		List<BTEntry> e1_1 = new LinkedList<BTEntry>();
		args.stream().forEach(arg -> e1_1.add(new BTEntry()));
		
		funEnv.add(ID, new BTEntry(e0));
		do {
			funEnv.openScope();
			for(int i = 0; i < e0.size(); i++)
				funEnv.add(args.get(i).getId(), (BTEntry) e0.get(i).clone());
			toRet.addAll(block.inferBehaviourSameScope(funEnv));
			for(int i = 0; i < e0.size(); i++) {
				e1.set(i, (BTEntry)e1_1.get(i).clone());
				e1_1.set(i, funEnv.getIDEntry(args.get(i).getId()));
			}
			funEnv.closeScope();
			funEnv.update(ID, new BTEntry(e1_1));
		} while(!BTEntry.areEqual(e1, e1_1));
		/**
		 * int f(int var x) {  int y; }
		 */
		e.add(ID, funEnv.getIDEntry(ID));
		
		return toRet;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		idEntry.label = CodeGenUtils.freshLabel();
		sb.newLine(idEntry.label, ":");
		sb.newLine("move $fp $sp");
		sb.newLine("push $ra 4");
		block._codeGen(nl+1, sb);
		sb.newLine("lw $ra 0($sp) 4");
		sb.newLine("addi $sp $sp ", Integer.toString(args.stream().map(Arg::getType).map(Type::getDimension).reduce((a,b) -> a + b).orElse(0) + 64));
		sb.newLine("lw $fp 0($sp) 4");
		sb.newLine("pop 4");
		sb.newLine("jr");
	}
}
