package ast.types;

/**
 * Represents the Integer type.
 */
public class IntType extends Type {

	public IntType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}

	@Override
	public EType getType() {
		return EType.INT;
	}

	@Override
	protected int _getDimension() {
		return 4;
	}
}
