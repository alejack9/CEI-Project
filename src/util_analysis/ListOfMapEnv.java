package util_analysis;

import java.util.HashMap;
import java.util.Stack;

import ast.STEntry;

public class ListOfMapEnv implements Environment {
	
	Stack<HashMap<String, STEntry>> scopes = new Stack<HashMap<String,STEntry>>();
	
	/**
	 * 
	 * @param id
	 * @param symTableEntry
	 */
	@Override
	public boolean addOrUpdate(String id, STEntry symTableEntry) {
		return scopes.peek().put(id, symTableEntry) == null;
	}
	
	/**
	 * 
	 * @param id
	 * @param symTableEntry
	 */
	@Override
	public boolean add(String id, STEntry symTableEntry) {
		return scopes.peek().putIfAbsent(id, symTableEntry) == null;
	}
	
	
	/** 
	 * Inserts a new scope into the environment.
	 * When a scope is inserted old scope is clone so previous defined
	 * variables still exist
	 */
	@Override
	public void openScope(){
		scopes.push(new HashMap<String, STEntry>());
	}
	
	
	/**
	 * Drops the current scope and returns to the outer scope
	 * removing all changes and additions done within this scope 
	 */
	@Override
	public void closeScope(){
		scopes.pop();
	}
	
	/**
	 * Given an id determines if the variable belongs to the environment
	 * this is to check the scopes from inner to outer looking for the variable
	 * @param id
	 */
	@Override
	public boolean containsID(String id) {
		return getIDEntry(id) != null;
	}

	@Override
	public boolean containsIDLocal(String id) {
		return getLocalIDEntry(id) != null;
	}
	
	/**
	 * Remove the variable with the given id from the first scope that contains it
	 * notice that if the variable exists in an outer scope it will have
	 * that value
	 * @param id
	 */
	@Override
	public STEntry deleteVariable(String id){
		for(HashMap<String, STEntry> scope:scopes)
			if(scope.containsKey(id)){
				return scope.remove(id);
			}
		return null;
	}
	
	/**
	 * Check for variable/function
	 * @param id of the variable/function
	 * @return STEntry associated with the variable/function, null if it is not declared
	 */
	@Override
	public STEntry getIDEntry(String id){
		for(HashMap<String, STEntry> scope:scopes){
			if(scope.containsKey(id)){
				return scope.get(id);				
			}
		}
		
		return null;
	}
	
	
	/**
	 * Check local scope for variable/function
	 * @param id of the variable/function
	 * @return STEntry associated with the variable/function in current scope, null otherwise
	 */
	@Override
	public STEntry getLocalIDEntry(String id) {
		return scopes.peek().get(id);		
		
	}

	@Override
	public int getNestingLevel() {
		return scopes.size() - 1;
	}


	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

}
