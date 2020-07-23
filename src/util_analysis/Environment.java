package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import util_analysis.entries.Entry;

public interface Environment<T extends Entry> extends Cloneable {
	public boolean add(String id, T entry);
	public T update(String id, T entry);
	public void openScope();
	public void closeScope();
	public boolean containsID(String id);
	public boolean containsIDLocal(String id);
	public T deleteVariable(String id);
	public T getIDEntry(String id);
	public T getLocalIDEntry(String id);
	public int getNestingLevel();
	public int getOffset();
	public void addOffset(int i);
	public void setOffset(int i);
	public Map<String, T> getAllIDs();
	public Object clone();
	public Environment<T> getCurrentScopeEnv();
	public Map<String,T> getCurrentScope();
	public void addScope(Map<String,T> currentScope);
	public LinkedList<HashMap<String, T>> getAllFunctions();
	@Override
	public boolean equals(Object obj);
}
