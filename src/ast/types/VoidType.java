package ast.types;

public class VoidType extends Type {

	public VoidType(boolean isParameter, boolean isRef) {
		super(isParameter, isRef);
	}
	
	public VoidType() {
		super(false, false);
	}

	@Override
	public EType getType() {
		return EType.VOID;
	}
}
