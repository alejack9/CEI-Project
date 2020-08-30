package util_analysis.entries;

import ast.types.EType;
import ast.types.Type;

/**
 * Symbol table entry
 */
public class STEntry extends Entry {
	
	private Type type;

	public int nestingLevel;
	
	public int offset;
	
	/**
	 * Function related label name to use as branch name in code generation.
	 */
	public String label;

	public STEntry(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean isFunction() {
		return type.getType().compareTo(EType.FUNCTION) == 0;
	}

	@Override
	public boolean _equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof STEntry))
			return false;
		STEntry casted = (STEntry) obj;

		if (!type.equals(casted.type) || nestingLevel != casted.nestingLevel || offset != casted.offset)
			return false;

		return true;
	}

	@Override
	public Object clone() {
		STEntry st = new STEntry((Type) type.clone());
		st.nestingLevel = nestingLevel;
		st.offset = offset;
		st.label = label;
		return st;
	}

}
