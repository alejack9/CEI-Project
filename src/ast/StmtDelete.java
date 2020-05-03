package ast;

import java.util.LinkedList;
import java.util.List;

import ast.descriptors.ParameterDescriptor;
import ast.errors.LocalVariableDoesntExistsError;
import ast.errors.SemanticError;
import ast.errors.VariableNotExistsError;
import ast.errors.VariableNotVarError;
import util_analysis.Environment;

public class StmtDelete extends Stmt {

	private String id;

	public StmtDelete(String id) {
		this.id = id;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		List<SemanticError> result = new LinkedList<SemanticError>();

		// If the variable does not exists, add a new error, otherwise...
		if (e.containsVariable(id))
			// ...if the variable exists in the local scope, delete the variable, otherwise...
			if (e.containsLocalVariable(id))
				e.deleteVariable(id);
			// ...at this point it is known that the variable exists but not in the local scope, so
			// if the variable is not a parameter, add a new error, otherwise...
			else if (e.getVariableType(id) instanceof ParameterDescriptor)
				// ...if the parameter is declared with "var" attribute then delete the variable, else
				// add a new error
				if (((ParameterDescriptor) e.getVariableType(id)).isVar())
					e.deleteVariable(id);
				else
					result.add(new VariableNotVarError(id));
			else
				result.add(new LocalVariableDoesntExistsError(id));
		else
			result.add(new VariableNotExistsError(id));

		return result;
	}

}
