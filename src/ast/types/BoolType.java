package ast.types;

public class BoolType extends Type {
	
	public BoolType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}
	public BoolType() {
		this(false, false);
	}
	
	@Override
	public EType getType() {
		return EType.BOOL;
	}
	@Override
	public int _getDimension() {
		return 8;
	}
}
