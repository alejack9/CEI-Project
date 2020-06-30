package ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTBase;
import behavioural_analysis.BTBlock;
import util_analysis.Environment;
import ast.errors.SemanticError;

public class SPStmtBlock extends SPStmt {
	
	private List<SPStmt> children;

	public SPStmtBlock(List<SPStmt> children) {
		this.children = children;
	}

	/**
	 * It checks the semantic for every child in order item by item
	 * It creates a new scope for the elements that will be found inside
	 * Each element may add new elements to the environment inside the current scope
	 * After finishing drop the newly created scope
	 */
	public List<SemanticError> checkSemantics(Environment e) {
		//initialize result variable
		List<SemanticError> toRet = Collections.emptyList();
		
		//create scope for inner elements
		e.openScope();
		
		//check children semantics
		if(children != null)
			for(SPStmt el : children)
				toRet.addAll(el.checkSemantics(e));
		
		//close scope for this block
		e.closeScope();
		
		return toRet;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		//create scope for inner elements
		e.openScope();
		
		BTBlock current = null;
		
		LinkedList<BTBase> behaviors = new LinkedList<BTBase>();
		for(SPStmt el:children)
			behaviors.push(el.inferBehavior(e));
		
		for(BTBase b:behaviors){
			current = BTBase.add(b,current);
		}
		
		//close scope for this block
		e.closeScope();
		
		return current;
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

}
