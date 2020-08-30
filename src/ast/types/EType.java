package ast.types;

/**
 * ID types and function return types.
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
	 * Compare current enum value with the enum type of the {@link Type Type} object
	 * passed.
	 *
	 * @param type object to compare
	 * @return true if equals, false otherwise
	 */
	public boolean equalsTo(Type type) {
		return (this == VOID && type == null) || (type != null && this.compareTo(type.getType()) == 0);
	}

	/**
	 * Get {@link Type Type} object from current enum value.
	 *
	 * @param isParameter if the ID related to the type is a parameter
	 * @param isRef       if the ID related to the type is required by reference<br>
	 *                    (if isParameter is false, isRef should be false)
	 * @return the related object Type
	 */
	public Type getType(boolean isParameter, boolean isRef) {
		return TypeFactory.getType(this, isParameter, isRef);
	}

	/**
	 * Get {@link Type Type} object from current enum value associated to a local
	 * variable.
	 *
	 * @return the correspondent object Type
	 */
	public Type getType() {
		return this.getType(false, false);
	}

	/**
	 * Get the current enum value associated to a string value.
	 *
	 * @param value the type name
	 * @return the related enum value
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static EType getEnum(String value) throws IllegalArgumentException {
		for (EType v : values())
			if (v.toString().equalsIgnoreCase(value))
				return v;

		throw new IllegalArgumentException();
	}
}
