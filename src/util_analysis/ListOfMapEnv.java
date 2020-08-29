/*
 * 
 */
package util_analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import util_analysis.entries.Entry;
import util_analysis.entries.STEntry;

/**
 * The Class ListOfMapEnv.
 *
 * @param <T> the entry type
 */
public class ListOfMapEnv<T extends Entry> implements Environment<T> {

	/** The scopes. */
	LinkedList<HashMap<String, T>> scopes = new LinkedList<HashMap<String, T>>();

	/** The nesting level. */
	private int nestingLevel;

	/** The offset. */
	private int offset = 0;

	/**
	 *
	 * @param existingScopes the existing scopes
	 * @param offset         the starting offset
	 * @param nestingLevel   starting the nesting level
	 */
	public ListOfMapEnv(LinkedList<HashMap<String, T>> existingScopes, int offset, int nestingLevel) {
		this.scopes = existingScopes;
		this.offset = offset;
		this.nestingLevel = nestingLevel;
	}

	public ListOfMapEnv() {
	}

	/**
	 * If deleted, restores the passed id.
	 *
	 * @param id    the id
	 * @param entry the entry
	 * @return true, if successful
	 */
	@Override
	public boolean add(String id, T entry) {
		T prev = getLocalIDEntry(id);
		if (prev != null && !prev.isDeleted())
			return false;

		entry.setDeleted(false);

		if (entry instanceof STEntry) {
			STEntry stEntry = (STEntry) entry;
			if (prev == null) {
				stEntry.offset = getOffset();
				this.offset += stEntry.getType().getDimension();
				stEntry.nestingLevel = getNestingLevel();
			}
		}

		scopes.peek().put(id, entry);
		return true;
	}

	/**
	 * Inserts a new scope into the environment
	 */
	@Override
	public void openScope() {
		scopes.push(new HashMap<String, T>());
	}

	/**
	 * Drops the current scope
	 */
	@Override
	public void closeScope() {
		scopes.pop();
	}

	@Override
	public T deleteVariable(String id) {
		T toRet = getIDEntry(id);
		if (toRet != null)
			toRet.setDeleted(true);
		return toRet;
	}

	@Override
	public T getIDEntry(String id) {
		for (HashMap<String, T> scope : scopes)
			if (scope.containsKey(id) && !scope.get(id).isDeleted())
				return scope.get(id);
		return null;
	}

	@Override
	public T getLocalIDEntry(String id) {
		return scopes.peek().get(id);
	}

	@Override
	public int getNestingLevel() {
		return nestingLevel;
	}

	@Override
	public T update(String id, T entry) {
		for (HashMap<String, T> scope : scopes)
			if (scope.containsKey(id) && !scope.get(id).isDeleted())
				return scope.put(id, entry);
		return null;
	}

	@Override
	public Map<String, T> getAllIDs() {
		Map<String, T> toRet = new HashMap<String, T>();

		/**
		 * Using the descending iterator, it is possible to replace existing ids values
		 * with a more specific one derived by the inner scope
		 */
		scopes.descendingIterator().forEachRemaining(scope -> toRet.putAll(scope));

		return toRet;
	}

	@Override
	public LinkedList<HashMap<String, T>> getAllFunctions() {
		LinkedList<HashMap<String, T>> toRet = new LinkedList<HashMap<String, T>>();
		scopes.descendingIterator().forEachRemaining(s -> {
			HashMap<String, T> toAdd = new HashMap<String, T>();

			/**
			 * <code>e.getValue() != null && (e.getValue().isFunction()</code> checks that
			 * the current entry is a function
			 */
			toAdd.putAll(s.entrySet().stream().filter(e -> e.getValue() != null && (e.getValue().isFunction()))
					.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue())));
			toRet.push(toAdd);
		});
		return toRet;
	}

	@Override
	public int getOffset() {
		return this.offset;
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public void increaseNestingLevel() {
		nestingLevel++;
	}

	@Override
	public void decreaseNestingLevel() {
		nestingLevel--;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;

		if (!obj.getClass().isInstance(this))
			return false;
		ListOfMapEnv<T> casted = (ListOfMapEnv<T>) obj;

		if (casted.scopes.size() != scopes.size())
			return false;

		for (int i = 0; i < scopes.size(); i++) {
			HashMap<String, T> thisScope = scopes.get(i);
			HashMap<String, T> objScope = casted.scopes.get(i);

			if (thisScope.size() != objScope.size())
				return false;

			Iterator<java.util.Map.Entry<String, T>> iterator = thisScope.entrySet().iterator();
			while (iterator.hasNext()) {
				java.util.Map.Entry<String, T> entry = iterator.next();
				if (!objScope.containsKey(entry.getKey()) || !objScope.get(entry.getKey()).equals(entry.getValue()))
					return false;
			}
		}

		return true;
	}

	/**
	 * Clone.
	 *
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		ListOfMapEnv<T> clonedEnv = null;

		try {
			clonedEnv = (ListOfMapEnv<T>) super.clone();
			LinkedList<HashMap<String, T>> clonedScopes = new LinkedList<HashMap<String, T>>();

			scopes.descendingIterator().forEachRemaining(scope -> {
				clonedScopes.push(new HashMap<String, T>());
				scope.forEach((k, v) -> clonedScopes.peek().put(k, (T) v.clone()));
			});

			clonedEnv.scopes = clonedScopes;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return clonedEnv;
	}
}
