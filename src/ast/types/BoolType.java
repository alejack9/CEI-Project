package ast.types;

/**
 * Represents the boolean type
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
	public int _getDimension() {
		return 1;
	}
}
