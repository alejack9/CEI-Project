package ast;

import java.util.LinkedList;
import java.util.List;

import javax.swing.border.EtchedBorder;

import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import ast.errors.TypeError;

public class StmtBlock extends Stmt {
	
	private List<Stmt> children;

	public StmtBlock(List<Stmt> children, int line, int column) {
		super(line, column);
		this.children = children;
	}

	/**
	 * It checks the semantic for every child in order item by item
	 * It creates a new scope for the elements that will be found inside
	 * Each element may add new elements to the environment inside the current scope
	 * After finishing drop the newly created scope
	 */
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		//initialize result variable
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		//create scope for inner elements
		e.openScope(); 
		int oldOffset = e.getOffset();
		toRet.addAll(checkSemanticsSameScope(e));
		//close scope for this block
		e.closeScope();
		e.setOffset(oldOffset);
		
		return toRet;
	}
	public List<SemanticError> checkSemanticsSameScope(Environment<STEntry> e){
		//initialize result variable
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		//check children semantics
		if(children != null)
			for(Stmt el : children)
				toRet.addAll(el.checkSemantics(e));
		return toRet;
	}
	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		//initialize result variable
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		//create scope for inner elements
		e.openScope();
		
		toRet.addAll(inferBehaviourSameScope(e));
		
		//close scope for this block
		e.closeScope();
		
		return toRet;
	}
	
	public List<BehaviourError> inferBehaviourSameScope(Environment<BTEntry> e){
		//initialize result variable
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
				
		//check children semantics
		if(children != null)
			for(Stmt el : children)
				toRet.addAll(el.inferBehaviour(e));
		
		return toRet;
	}
	@Override
	public Type inferType() {
		Type toRet = null;
		EType lastRetT = null;
		boolean changed = false;
		boolean safe = false;
	
		for (Stmt c : children) {
			toRet = c.inferType();
			
			if (toRet != null) {
				if (lastRetT != null && !lastRetT.equalsTo(toRet))
					changed = true;
				else lastRetT = toRet.getType();
				if (c instanceof StmtIte) {
					if (((StmtIte)c).hasElseStmt()) safe = true;
				}
				else safe = true;
			}
		
		}

		if (changed)
			TypeErrorsStorage.addError(new TypeError("Inconsistent return types in this block", line, column));

		
		if (!safe && toRet != null && !EType.VOID.equalsTo(toRet))
			TypeErrorsStorage.addError(new TypeError("Unsafe return (the function does not always return)", line, column));
		
		return toRet;
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			
//			
//			if (!EType.VOID.equalsTo(toRet) && !toRet.getType().equalsTo(newType))
//				changed = true;
//			if (c instanceof StmtIte && ((StmtIte)c).hasElseStmt() || c instanceof StmtRet)
//				safe = true;
//			
//			toRet = newType;
//		}
		
		
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) {
		for (Stmt c : children)
			c._codeGen(nl, sb);
	}

}
