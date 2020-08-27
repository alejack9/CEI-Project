package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.errors.VariableNotExistsError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import support.CodeGenUtils;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.DeletedVariableError;
import ast.errors.SemanticError;

public class StmtAssignment extends Stmt{
	
	private String id;
	private STEntry idEntry;
	private Exp exp;

	public StmtAssignment(String id, Exp exp, int line, int column) {
		super(line, column);
		this.id = id;
		this.exp = exp;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();

		idEntry = e.getIDEntry(id);
		
		if(idEntry == null)
			toRet.add(new VariableNotExistsError(id, line, column));
				
		toRet.addAll(exp.checkSemantics(e));

		return toRet;		
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
	
		toRet.addAll(exp.inferBehaviour(e));
		
		e.getIDEntry(id).setLocalEffect(BTHelper.seq(e.getIDEntry(id).getLocalEffect(), EEffect.RW));
		
	    if(e.getIDEntry(id).getLocalEffect().equals(EEffect.T))
	      toRet.add(new DeletedVariableError(id, line, column));
	    
	    return toRet;
	}

	@Override
	public Type inferType() {
		Type expType = exp.inferType(); 
		if(!expType.getType().equalsTo(idEntry.getType()))
			TypeErrorsStorage.addError(new TypeError("Variable type (" + idEntry.getType() + ") is not equal to expression type (" + expType +")", line, column));
		
		return null;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		exp._codeGen(nl, sb);
		sb.newLine("move $t1 $a0");
		
		if(idEntry.getType().IsParameter()) {
			CodeGenUtils.getVariableCodeGen(idEntry, nl, sb);
			
			if(idEntry.getType().IsRef())
				sb.newLine("sw $t1 0($a0) ", Integer.toString(idEntry.getType().getDimension()));
			else 
				sb.newLine("sw $t1 ", Integer.toString(idEntry.getOffset()), "($al) ", Integer.toString(idEntry.getType().getDimension()));
		}
		else {
			sb.newLine("li $t1 0");
			sb.newLine("sw $a0 ", Integer.toString(idEntry.getOffset()), "($t1) ", Integer.toString(idEntry.getType().getDimension()));
		}
	}
}
