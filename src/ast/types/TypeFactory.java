package ast.types;

class TypeFactory {

	private TypeFactory() {
	}

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
