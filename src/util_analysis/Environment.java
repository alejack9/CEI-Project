package util_analysis;

public abstract class Environment<T> implements Cloneable {
	public abstract boolean add(String id, T entry);
	public abstract boolean update(String id, T entry);
	public abstract void openScope();
	public abstract void closeScope();
	public abstract boolean containsID(String id);
	public abstract boolean containsIDLocal(String id);
	public abstract T deleteVariable(String id);
	public abstract T getIDEntry(String id);
	public abstract T getLocalIDEntry(String id);
	public abstract int getNestingLevel();
	public abstract int getOffset();
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
