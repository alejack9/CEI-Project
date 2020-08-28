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

// TODO: Auto-generated Javadoc
/**
 * The Class ListOfMapEnv.
 *
 * @param <T> the generic type
 */
public class ListOfMapEnv<T extends Entry> implements Environment<T> {

	/** The scopes. */
	LinkedList<HashMap<String, T>> scopes = new LinkedList<HashMap<String, T>>();

	/** The nesting level. */
	private int nestingLevel;

	/** The offset. */
	private int offset = 0;

	/**
	 * Instantiates a new list of map env.
	 *
	 * @param existingScopes the existing scopes
	 * @param offset the offset
	 * @param nestingLevel the nesting level
	 */
	public ListOfMapEnv(LinkedList<HashMap<String, T>> existingScopes, int offset, int nestingLevel) {
		this.scopes = existingScopes;
		this.offset = offset;
		this.nestingLevel = nestingLevel;
	}

	/**
	 * Instantiates a new list of map env.
	 */
	public ListOfMapEnv() {
	}

	/**
	 * Adds the.
	 *
	 * @param id the id
	 * @param entry the entry
	 * @return true, if successful
	 */
	@Override
	public boolean add(String id, T entry) {
		entry.setDeleted(false);
		if (entry instanceof STEntry)
			return add(id, (STEntry) entry);
		T prev = getLocalIDEntry(id);
		if (prev == null || prev.isDeleted()) {
			scopes.peek().put(id, entry);
			return true;
		}
		return false;
	}

	/**
	 * Adds the.
	 *
	 * @param id the id
	 * @param stEntry the st entry
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	private boolean add(String id, STEntry stEntry) {
		T prev = getLocalIDEntry(id);
		if (prev == null || prev.isDeleted()) {
			if (prev == null) {
				stEntry.offset = getOffset();
				this.offset += stEntry.getType().getDimension();
				stEntry.nestingLevel = getNestingLevel();
			}
			scopes.peek().put(id, (T) stEntry);
			return true;
		}
		return false;
	}

	/**
	 * Inserts a new scope into the environment. When a scope is inserted old scope
	 * is clone so previous defined variables still exist
	 */
	@Override
	public void openScope() {
		scopes.push(new HashMap<String, T>());
	}

	/**
	 * Drops the current scope and returns to the outer scope removing all changes
	 * and additions done within this scope.
	 */
	@Override
	public void closeScope() {
		scopes.pop();
	}

	/**
	 * Remove the variable with the given id from the first scope that contains it
	 * notice that if the variable exists in an outer scope it will have that value.
	 *
	 * @param id the id
	 * @return the t
	 */
	@Override
	public T deleteVariable(String id) {
		T toRet = getIDEntry(id);
		if (toRet != null)
			toRet.setDeleted(true);
		return toRet;
	}

	/**
	 * Check for variable/function.
	 *
	 * @param id of the variable/function
	 * @return T associated with the variable/function, null if it is not declared
	 */
	@Override
	public T getIDEntry(String id) {
		for (HashMap<String, T> scope : scopes)
			if (scope.containsKey(id) && !scope.get(id).isDeleted())
				return scope.get(id);
		return null;
	}

	/**
	 * Check local scope for variable/function.
	 *
	 * @param id of the variable/function
	 * @return T associated with the variable/function in current scope, null
	 *         otherwise
	 */
	@Override
	public T getLocalIDEntry(String id) {
		return scopes.peek().get(id);
	}

	/**
	 * Gets the nesting level.
	 *
	 * @return the nesting level
	 */
	@Override
	public int getNestingLevel() {
		return nestingLevel;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	@Override
	public int getOffset() {
		return this.offset;
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param entry the entry
	 * @return the t
	 */
	@Override
	public T update(String id, T entry) {
		for (HashMap<String, T> scope : scopes)
			if (scope.containsKey(id) && !scope.get(id).isDeleted())
				return scope.put(id, entry);
		return null;
	}

	/**
	 * Gets the all I ds.
	 *
	 * @return the all I ds
	 */
	@Override
	public Map<String, T> getAllIDs() {
		Map<String, T> toRet = new HashMap<String, T>();

		scopes.descendingIterator().forEachRemaining(scope -> toRet.putAll(scope));

		return toRet;
	}

	/**
	 * Gets the all functions.
	 *
	 * @return the all functions
	 */
	@Override
	public LinkedList<HashMap<String, T>> getAllFunctions() {
		LinkedList<HashMap<String, T>> toRet = new LinkedList<HashMap<String, T>>();
		scopes.descendingIterator().forEachRemaining(s -> {
			HashMap<String, T> toAdd = new HashMap<String, T>();
			toAdd.putAll(s.entrySet().stream().filter(e -> isFun(e.getValue()))
					.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue())));
			toRet.push(toAdd);
		});
		return toRet;
	}

	/**
	 * Checks if is fun.
	 *
	 * @param entry the entry
	 * @return true, if is fun
	 */
	private boolean isFun(T entry) {
		return entry != null && (entry.isFunction());
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset the new offset
	 */
	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Increase nesting level.
	 */
	@Override
	public void increaseNestingLevel() {
		nestingLevel++;
	}

	/**
	 * Decrease nesting level.
	 */
	@Override
	public void decreaseNestingLevel() {
		nestingLevel--;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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
