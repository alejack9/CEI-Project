package ast.types;

public abstract class Type {
	public abstract EType getType();
	
	@Override
	public String toString() {
		return this.getType().toString();
	}
	
	@Override
	public boolean equals(Object e) {
		if(!(e instanceof Type)) return false;
		if(this.getType() != ((Type)e).getType()) return false;
		return true;
	}
	
}
