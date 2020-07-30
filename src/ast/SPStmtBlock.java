package ast;

import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

public class SPStmtBlock extends SPStmt {
	
	private List<SPStmt> children;

	public SPStmtBlock(List<SPStmt> children, int line, int column) {
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
			for(SPStmt el : children)
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
			for(SPStmt el : children)
				toRet.addAll(el.inferBehaviour(e));
		
		return toRet;
	}
	@Override
	public Type inferType() {
		Type toRet = EType.VOID.getType();
		
		// assignment is not an heavy operation and we should
		// make further checks using another approaches
		for (SPStmt c : children)
			toRet = c.inferType();
		
		return toRet;
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SPStmtBlock");
		for (SPStmt c : children)
			c._codeGen(nl, sb);
	}

}
