package ast.types;

/**
 * ID and function return types.
 */
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

	/**
	 * Compares enum value with the enum type of the <i>Type</i> object passed.
	 *
	 * @param type Type object to compare
	 * @return true if equals, false otherwise
	 */
	public boolean equalsTo(Type type) {
		return (this == VOID && type == null) || (type != null && this.compareTo(type.getType()) == 0);
	}

	/**
	 * Get <i>Type</i> object from enum value.
	 *
	 * @param isParameter the ID related to the type is a parameter
	 * @param isRef       the ID related to the type is passed by reference
	 * @return the related object Type
	 */
	public Type getType(boolean isParameter, boolean isRef) {
		return TypeFactory.getType(this, isParameter, isRef);
	}

	/**
	 * Gets <i>Type</i> object from enum value associated to a not parameter and not
	 * referenced ID.
	 *
	 * @return the correspondent object Type
	 */
	public Type getType() {
		return this.getType(false, false);
	}

	/**
	 * Gets the enum value associated to a string value.
	 *
	 * @param value the type name
	 * @return the related enum value
	 */
	public static EType getEnum(String value) {
		for (EType v : values())
			if (v.toString().equalsIgnoreCase(value))
				return v;

		throw new IllegalArgumentException();
	}
}
