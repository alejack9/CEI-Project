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

		if (e.containsVariable(id))
			if (e.containsVariableLocal(id))
				e.deleteVariable(id);
			else if (e.getVariableType(id) instanceof ParameterDescriptor)
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
