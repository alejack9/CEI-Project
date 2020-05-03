package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import ast.descriptors.Descriptor;
import ast.descriptors.FunctionDescriptor;
import ast.descriptors.ParameterDescriptor;
import ast.descriptors.VariableDescriptor;

public class Environment {

	/**
	 * contains the stack of scopes. the last one is always the current active scope
	 * this linked list is used as a stack with LIFO behavior
	 */
	LinkedList<HashMap<String, Descriptor>> scopes = new LinkedList<HashMap<String, Descriptor>>();

	/**
	 * Add a variable to the current scope
	 * @param id
	 * @param type The type of a variable (for instance Int or Bool)
	 */
	public void addVariable(String id, String type) {
		scopes.peek().put(id, new VariableDescriptor(id, type));
	}

	
	/**
	 * Add a function to the current scope
	 * @param id
	 * @param returnType
	 */
	public void addFunction(String id, String returnType) {
		scopes.peek().put(id,
				new FunctionDescriptor(id, returnType,
						// passing all the ParamaterDescriptors in the local scope as list to the FunctionDescriptor
						scopes.peek().values().stream().filter(s -> s instanceof ParameterDescriptor)
								.map(s -> (ParameterDescriptor) s).collect(Collectors.toCollection(LinkedList::new))));
	}

	/**
	 * Turn an environment variable into a Parameter 
	 * @param id
	 * @param var True if the parameter is referenced, False otherwise
	 */
	public void turnIntoParameter(String id, boolean var) {
		scopes.peek().put(id, new ParameterDescriptor((VariableDescriptor) scopes.peek().get(id), var));
	}

	/**
	 * Inserts a new scope into the environment. When a scope is inserted old scope
	 * is clone so previous defined variables still exist
	 */
	public void openScope() {
		scopes.push(new HashMap<String, Descriptor>());
	}

	/**
	 * Drops the current scope and returns to the outer scope removing all changes
	 * and additions done within this scope
	 */
	public void closeScope() {
		scopes.pop();
	}

	/**
	 * Given an id determines if the variable or function belongs to the environment
	 * this is to check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean contains(String id) {
		return scopes.stream().anyMatch(scope -> scope.containsKey(id));
	}

	/**
	 * Given an id determines if the variable belongs to the environment this is to
	 * check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsVariable(String id) {
		return scopes.stream().anyMatch(scope -> {
			return scope.get(id) != null && scope.get(id).getType().charAt(0) != '(';
		});
	}

	/**
	 * Given an id determines if the function belongs to the environment this is to
	 * check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsFunction(String id) {
		return scopes.stream().anyMatch(scope -> {
			return scope.get(id) != null && scope.get(id).getType().charAt(0) == '(';
		});
	}

	/**
	 * Remove the variable with the given id from the first scope that contains it.
	 * Notice that if the variable exists in an outer scope it will have that value
	 * 
	 * @param id
	 */
	public void deleteVariable(String id) {
		scopes.stream().filter(s -> s.containsKey(id)).findFirst().ifPresent(hm -> hm.remove(id));
	}

	/**
	 * Check for a variable in the Global scope
	 * 
	 * @param id
	 * @return The descriptor of the variable (if any) or NULL.
	 */
	public Descriptor getVariableType(String id) {
		return scopes.stream().filter(s -> s.containsKey(id)).map(hm -> hm.get(id)).findFirst().orElse(null);
	}

	/**
	 * Check for a variable in the Local Scope
	 * 
	 * @param id
	 * @return The descriptor of the variable (if any) or NULL.
	 */
	public Descriptor getLocalVariableValue(String id) {
		return scopes.peek().get(id);
	}

	/**
	 * Given an id determines if the variable or function belongs to the environment
	 * this is to check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsLocal(String id) {
		return scopes.peek().containsKey(id);
	}

	/**
	 * Given an id determines if the function belongs to the environment this is to
	 * check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsLocalFunction(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) == '(';
	}

	/**
	 * Given an id determines if the variable belongs to the local environment.
	 * This is to check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsLocalVariable(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) != '(';
	}
}