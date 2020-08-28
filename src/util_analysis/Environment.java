package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import support.MyCloneable;
import util_analysis.entries.Entry;

public interface Environment<T extends Entry> extends MyCloneable {
	public boolean add(String id, T entry);

	public T update(String id, T entry);

	public void openScope();

	public void closeScope();

	public T deleteVariable(String id);

	public T getIDEntry(String id);

	public T getLocalIDEntry(String id);

	public void increaseNestingLevel();

	public void decreaseNestingLevel();

	public int getNestingLevel();

	public void setOffset(int offset);

	public int getOffset();

	public Map<String, T> getAllIDs();

	public LinkedList<HashMap<String, T>> getAllFunctions();

	@Override
	public Object clone();

	@Override
	public boolean equals(Object obj);
}
