package ast.types;

public class TypeFactory {
	
	private TypeFactory() {
		
	}
	
	public Type getType(EType type) {
		switch (type) {
		case INT: return new IntType();
		case BOOL: return new BoolType();
		case VOID: return new VoidType();
		case FUNCTION: return new ArrowType();
		default: return null;
		}
	}
}
