package ast;

import java.util.List;

import behavioural_analysis.BTBase;
import util_analysis.Environment;
import util_analysis.SemanticError;

public class SPStmtDecFun extends SPStmtDec {

	String type;
	String ID;
	List<SPArg> args;
	SPStmtBlock block;
	
	public SPStmtDecFun(String type, String ID, List<SPArg> args, SPStmtBlock block) {
		this.type = type;
		this.ID = ID;
		this.args = args;
		this.block = block;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTBase inferBehavior(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SPElementBase inferType(Environment e) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
