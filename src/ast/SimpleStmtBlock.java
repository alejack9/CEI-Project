package ast;

import java.util.LinkedList;
import java.util.List;

import behavioural_analysis.BTBase;
import behavioural_analysis.BTBlock;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SimpleStmtBlock extends SimpleStmt {
	
	List<SimpleStmt> children;

	public SimpleStmtBlock(List<SimpleStmt> children) {
		this.children = children;
	}

	/**
	 * It checks the semantic for every child in order item by item
	 * It creates a new scope for the elements that will be found inside
	 * Each element may add new elements to the environment inside the current scope
	 * After finishing drop the newly created scope
	 */
	public List<SemanticError> checkSemantics(Environment e) {
		//create scope for inner elements
		e.openScope();
		
		//initialize result variable
		LinkedList<SemanticError> result = new LinkedList<SemanticError>();
		
		//check children semantics
		if(children!=null)
			for(SimpleStmt el:children)
				result.addAll(el.checkSemantics(e));
		
		//close scope for this block
		e.closeScope();
		
		return result;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		//create scope for inner elements
		e.openScope();
		
		BTBlock current = null;
		
		LinkedList<BTBase> behaviors = new LinkedList<BTBase>();
		for(SimpleStmt el:children)
			behaviors.push(el.inferBehavior(e));
		
		for(BTBase b:behaviors){
			current = BTBase.add(b,current);
		}
		
		//close scope for this block
		e.closeScope();
		
		return current;
	}

}
