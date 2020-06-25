package ast.types;

public class VoidType extends Type {

	@Override
	public EType getType() {
		return EType.VOID;
	}
}
