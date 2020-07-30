package ast;

import java.util.List;

import ast.types.Type;
import support.CustomStringBuilder;
import util_analysis.Environment;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

public class SPExpCall extends SPExp {

	private SPStmtCall call;
	
	public SPExpCall(SPStmtCall call, int line, int column) {
		super(line, column);
		this.call = call;
	}

	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		return call.checkSemantics(e);
	}

	@Override
	public Type inferType() {
		return call.inferType();
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		return call.inferBehaviour(e);
	}

	@Override
	public void _codeGen(int nl, CustomStringBuilder sb) { String prev = ""; for(int i = 0; i <= nl; i++) prev += "\t";
		sb.newLine(prev, "# SPExpCall");
		call._codeGen(nl, sb);
	}
	
}
