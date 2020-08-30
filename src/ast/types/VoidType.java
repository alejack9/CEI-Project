package ast.types;

/**
 * Represents the Void type.
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
	protected int _getDimension() {
		return 0;
	}
}
