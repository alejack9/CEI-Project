package util_analysis.entries;

import ast.types.EType;
import ast.types.Type;

public class STEntry extends Entry {
	private Type type;
	private int nestingLevel;
	private int offset;
	
	public STEntry(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	public int getNestingLevel() {
		return nestingLevel;
	}
	public void setNestingLevel(int val) {
		nestingLevel = val;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int val) {
		offset = val;
	}
	
	@Override
	public Object clone() {
		STEntry st = new STEntry((Type) type.clone());
		st.nestingLevel = nestingLevel;
		st.offset = offset;
		return st;
	}

	@Override
	public boolean IsFunction() {
		return type.getType().compareTo(EType.FUNCTION) == 0;
	}

	@Override
	public boolean _equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		if(!(obj instanceof STEntry)) return false;
		STEntry casted = (STEntry) obj;
		
		if(!type.equals(casted.type)
				|| nestingLevel != casted.nestingLevel || offset != casted.offset) return false;
		
		return true;
	}

}
