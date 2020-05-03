package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

import ast.descriptors.Descriptor;
import ast.descriptors.FunctionDescriptor;
import ast.descriptors.ParameterDescriptor;
import ast.descriptors.VariableDescriptor;

public class Environment {

	
	
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

	
	public void openScope() {
		scopes.push(new HashMap<String, Descriptor>());
	}

	
	public void closeScope() {
		scopes.pop();
	}

	
	public boolean contains(String id) {
		return scopes.stream().anyMatch(scope -> scope.containsKey(id));
	}

	
	public boolean containsVariable(String id) {
		return scopes.stream().anyMatch(scope -> {
			Descriptor ste = scope.get(id);
			return ste != null && ste.getType().charAt(0) != '(';
		});
	}

	
	public boolean containsFunction(String id) {
		return scopes.stream().anyMatch(scope -> {
			Descriptor ste = scope.get(id);
			return ste != null && ste.getType().charAt(0) == '(';
		});
	}

	
	public void deleteVariable(String id) {
		for (HashMap<String, Descriptor> scope : scopes) {
			if (scope.containsKey(id)) {
				scope.remove(id);
				return;
			}
		}
	}

	
	public Descriptor getVariableType(String id) {
		for (HashMap<String, Descriptor> scope : scopes) {
			if (scope.containsKey(id)) {
				return scope.get(id);
			}
		}
		return null;
	}

	
	public Descriptor getVariableValueLocal(String id) {

		return scopes.peek().get(id);

	}

	
	public boolean containsLocal(String id) {
		return scopes.peek().containsKey(id);
	}

	
	public boolean containsFunctionLocal(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) == '(';
	}

	
	public boolean containsVariableLocal(String id) {
		return scopes.peek().get(id) != null && scopes.peek().get(id).getType().charAt(0) != '(';
	}

}
