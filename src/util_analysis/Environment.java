package util_analysis;

public interface Environment<T> {
	public boolean add(String id, T entry);
	public void openScope();
	public void closeScope();
	public boolean containsID(String id);
	public boolean containsIDLocal(String id);
	public T deleteVariable(String id);
	public T getIDEntry(String id);
	public T getLocalIDEntry(String id);
	public int getNestingLevel();
	public int getOffset();
}
