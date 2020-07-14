package ast.types;

public abstract class Type implements Cloneable {
	
	public abstract EType getType();

	private boolean isParameter;
	
	private boolean isRef;

	protected Type(boolean isParameter, boolean isRef) {
		this.isParameter = isParameter;
		this.isRef = isRef;
	}
	
	protected Type() {
		this(false, false);
	}
	public boolean IsParameter() {
		return this.isParameter;
	}
	public boolean IsRef() {
		return this.isRef;
	}
	
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
	
	@Override
	public final Object clone() {
		return getType().getType(isParameter, isRef);
	}
	
}
