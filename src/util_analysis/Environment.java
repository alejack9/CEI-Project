package util_analysis;

import java.util.Map;

public interface Environment<T extends Cloneable> extends Cloneable {
	public boolean add(String id, T entry);
	public boolean update(String id, T entry);
	public void openScope();
	public void closeScope();
	public boolean containsID(String id);
	public boolean containsIDLocal(String id);
	public T deleteVariable(String id);
	public T getIDEntry(String id);
	public T getLocalIDEntry(String id);
	public int getNestingLevel();
	public int getOffset();
	public Map<String, T> getAllVariables();
	public Object clone();
}
