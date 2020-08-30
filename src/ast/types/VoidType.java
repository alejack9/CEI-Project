package ast.types;

/**
 * Represents void type
 */
public class VoidType extends Type {

	public VoidType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}

	@Override
	public EType getType() {
		return EType.VOID;
	}

	@Override
	public int _getDimension() {
		return 0;
	}
}
