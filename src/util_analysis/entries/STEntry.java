package util_analysis.entries;

import ast.types.EType;
import ast.types.Type;

public class STEntry implements Entry {
	private Type type;
	private int nestingLevel;
	private int offset;
	
	public STEntry(Type type, int nestingLevel, int offset) {
		this.type = type;
		this.nestingLevel = nestingLevel;
		this.offset = offset;
	}
	
	public Type getType() {
		return type;
	}
	public int getNestingLevel() {
		return nestingLevel;
	}
	public int getOffset() {
		return offset;
	}
	public Object clone() {
		return new STEntry((Type) type.clone(), nestingLevel, offset);
	}

	@Override
	public boolean IsFunction() {
		return type.getType().compareTo(EType.FUNCTION) == 0;
	}

}
