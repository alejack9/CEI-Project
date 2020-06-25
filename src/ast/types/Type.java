package ast.types;

public abstract class Type {
	public abstract EType getType();
	
	@Override
	public String toString() {
		return this.getType().toString();
	}
	
}
