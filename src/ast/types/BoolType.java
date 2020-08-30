package ast.types;

/**
 * Represents the Boolean type.
 */
public class BoolType extends Type {

	public BoolType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}

	@Override
	public EType getType() {
		return EType.BOOL;
	}

	@Override
	protected int _getDimension() {
		return 1;
	}
}
