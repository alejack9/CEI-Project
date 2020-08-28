package ast.types;

public enum EType {

	INT("int"), BOOL("bool"), VOID("void"), FUNCTION("function");

	private final String type;

	EType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

	public boolean equalsTo(Type type) {
		return (this == VOID && type == null) || (type != null && this.compareTo(type.getType()) == 0);
	}

	public Type getType(boolean isParameter, boolean isRef) {
		return TypeFactory.getType(this, isParameter, isRef);
	}

	public Type getType(boolean isParameter) {
		return this.getType(isParameter, false);
	}

	public Type getType() {
		return this.getType(false);
	}

	public static EType getEnum(String value) {
		for (EType v : values())
			if (v.toString().equalsIgnoreCase(value))
				return v;

		throw new IllegalArgumentException();
	}
}
