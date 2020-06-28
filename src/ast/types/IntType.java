package ast.types;

public class IntType extends Type {

	public IntType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}
	public IntType() {
		this(false, false);
	}

	@Override
	public EType getType() {
		return EType.INT;
	}
}
