package ast.types;

public enum EType {
	INT("int"),
	BOOL("bool"),
	VOID("void"),
	FUNCTION("function");
	
	private final String type;
	EType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}
	
	public boolean compareTo(String type) {
		return this.type.equals(type);
	}

	public Type getType() {
		return TypeFactory.getType(this);
	}
}

