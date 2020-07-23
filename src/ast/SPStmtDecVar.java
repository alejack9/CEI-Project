package ast;

import java.util.LinkedList;
import java.util.List;
import ast.errors.TypeError;
import ast.types.EType;
import ast.types.Type;
import behavioural_analysis.BTHelper;
import behavioural_analysis.EEffect;
import util_analysis.Environment;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;
import ast.errors.BehaviourError;
import ast.errors.IdAlreadytExistsError;
import ast.errors.SemanticError;

public class SPStmtDecVar extends SPStmtDec {

	private Type type;
	private String ID;
	private SPExp exp;
	
	public SPStmtDecVar(Type type, String ID, SPExp exp, int line, int column) {
		super(line, column);
		this.type = type;
		this.ID = ID;
		this.exp = exp;
	}
	
	@Override
	public List<SemanticError> checkSemantics(Environment<STEntry> e) {
		List<SemanticError> toRet = new LinkedList<SemanticError>();
		
		e.addOffset(-type.getDimension());
		if (!e.add(ID, new STEntry(type, e.getNestingLevel(), e.getOffset())))
			toRet.add(new IdAlreadytExistsError(ID, line, column));

		if(exp != null)
			toRet.addAll(exp.checkSemantics(e));
		
		return toRet;
	}

	@Override
	public List<BehaviourError> inferBehaviour(Environment<BTEntry> e) {
		List<BehaviourError> toRet = new LinkedList<BehaviourError>();
		
		BTEntry prev = e.getLocalIDEntry(ID);
		
		// Should be added after the exp check (if any) but this needs two "exp == null" controls instead of one
		// "checkSemantics" checks the correct usage of the variable so we can skip the check
		if(prev != null)
			e.update(ID, new BTEntry(BTHelper.seq(prev.getEffect(), EEffect.BOTTOM)));
		else
			e.add(ID, new BTEntry());
		
		if(exp != null) {
			toRet.addAll(exp.inferBehaviour(e));
			// "exp" cannot change the value of ID, so seq(bottom, rw) is always rw
			e.update(ID, new BTEntry(EEffect.RW));
		}
		
		return toRet;
	}

	@Override
	public Type inferType() {
		if (EType.VOID.equalsTo(type))
			TypeErrorsStorage.addError(new TypeError("Variable type cannot be void", line, column));
		
		if(exp != null) {
			Type expType = exp.inferType();
			if (!expType.getType().equalsTo(type))
				TypeErrorsStorage.addError(new TypeError("Expression type (" + expType + ") is not equal to variable type (" + type + ")", this.exp.line, this.exp.column));
		}
				
		return EType.VOID.getType();
	}
	
}
