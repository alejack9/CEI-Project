package ast.types;

public abstract class Type implements Cloneable {
	
	public abstract EType getType();

	protected boolean isParameter;
	protected boolean isRef;

	protected Type(boolean isParameter, boolean isRef) {
		this.isParameter = isParameter;
		this.isRef = isRef;
	}
	
	protected Type() {
		this(false, false);
	}
	
	protected abstract int _getDimension();
	
	public int getDimension() {
		return isRef ? 32 : _getDimension();
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
		if(e == null) return false;
		if(this == e) return true;
		if(!(e instanceof Type)) return false;

		Type casted = (Type)e;
		if(casted.getType().compareTo(getType()) != 0) return false;
		if(isParameter != casted.isParameter || isRef != casted.isRef) return false;
		
		return true;
	}
	
	@Override
	public Object clone() {
		return getType().getType(isParameter, isRef);
	}
	
}
