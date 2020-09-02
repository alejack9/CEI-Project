package ast.types;

/**
 * A factory for creating Type objects.
 */
class TypeFactory {

	private TypeFactory() {
	}

	/**
	 * Create the type associated to the passed {@link EType EType} value.
	 *
	 * @param type        the Etype value
	 * @param isParameter the related variable is a parameter
	 * @param isRef       the related variable is a parameter required by reference
	 * @return the Type object
	 */
	public static Type getType(EType type, boolean isParameter, boolean isRef) {
		switch (type) {
		case INT:
			return new IntType(isParameter, isRef);
		case BOOL:
			return new BoolType(isParameter, isRef);
		case VOID:
			return new VoidType(isParameter, isRef);
		case FUNCTION:
			return new ArrowType();
		default:
			return null;
		}
	}
}
