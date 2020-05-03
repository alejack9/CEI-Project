package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import ast.Descriptor;
import ast.FunctionDescriptor;
import ast.ParameterDescriptor;
import ast.VariableDescriptor;

public class Environment {

	// contains the stack of scopes. the last one is always the current active scope
	// this linked list is used as a stack with LIFO behavior
	LinkedList<HashMap<String, Descriptor>> scopes = new LinkedList<HashMap<String, Descriptor>>();

	public void addVariable(String id, String type) {
		scopes.peek().put(id, new VariableDescriptor(id, type));
	}

	public void addFunction(String id, String returnType) {
		scopes.peek().put(id,
				new FunctionDescriptor(id, returnType,
						scopes.peek().values().stream().filter(s -> s instanceof ParameterDescriptor)
								.map(s -> (ParameterDescriptor) s).collect(Collectors.toCollection(LinkedList::new))));
	}

	public void turnIntoParameter(String id, boolean var) {
		VariableDescriptor old = (VariableDescriptor)scopes.peek().get(id);
		scopes.peek().put(id, new ParameterDescriptor(old, var));
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
			Descriptor ste = scope.get(id);
			return ste != null && ste.getType().charAt(0) != '(';
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
			Descriptor ste = scope.get(id);
			return ste != null && ste.getType().charAt(0) == '(';
		});
	}

	/**
	 * Remove the variable with the given id from the first scope that contains it
	 * notice that if the variable exists in an outer scope it will have that value
	 * 
	 * @param id
	 */
	public void deleteVariable(String id) {
		for (HashMap<String, Descriptor> scope : scopes) {
			if (scope.containsKey(id)) {
				scope.remove(id);
				return;
			}
		}
	}

	/**
	 * Check for variable
	 * 
	 * @param id of the variable
	 * @return variable value, null if the variable doesnt exist
	 */
	public Descriptor getVariableType(String id) {
		for (HashMap<String, Descriptor> scope : scopes) {
			if (scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}

	/**
	 * Check local scope for variable
	 * 
	 * @param id of the variable
	 * @return variable value in current scope, null otherwise
	 */
	public Descriptor getVariableValueLocal(String id) {

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
	public boolean containsFunctionLocal(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) == '(';
	}

	/**
	 * Given an id determines if the variable belongs to the environment this is to
	 * check the scopes from inner to outer looking for the variable
	 * 
	 * @param id
	 */
	public boolean containsVariableLocal(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) != '(';
	}

}
