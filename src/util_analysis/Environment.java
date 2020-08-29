/*
 * 
 */
package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import support.MyCloneable;
import util_analysis.entries.Entry;

/**
 * The Interface Environment.
 *
 * @param <T> the entry type
 */
public interface Environment<T extends Entry> extends MyCloneable {
	
	/**
	 * Adds an entry in the current scope.
	 *
	 * @param id    the id
	 * @param entry the entry
	 * @return true, if successful
	 */
	public boolean add(String id, T entry);

	/**
	 * Updates an entry, if it exists.
	 *
	 * @param id    the id
	 * @param entry the entry
	 * @return the previous value associated with key, or null if there was no ids
	 *         in any scope
	 */
	public T update(String id, T entry);

	/**
	 * Opens scope.
	 */
	public void openScope();

	/**
	 * Closes scope.
	 */
	public void closeScope();

	/**
	 * Deletes a variable if it exists, starting from the closer scope and scanning
	 * everyone outer.
	 *
	 * @param id the id of the variable to delete
	 * @return the previous value associated with key, or null if there was no ids
	 *         in any scope
	 */
	public T deleteVariable(String id);

	/**
	 * Gets the ID entry.
	 *
	 * @param id the id
	 * @return the ID entry or null
	 */
	public T getIDEntry(String id);

	/**
	 * Gets the local ID entry.
	 *
	 * @param id the id
	 * @return the local ID entry or null
	 */
	public T getLocalIDEntry(String id);

	/**
	 * Increase nesting level.
	 */
	public void increaseNestingLevel();

	/**
	 * Decrease nesting level.
	 */
	public void decreaseNestingLevel();

	/**
	 * Gets the nesting level.
	 *
	 * @return the nesting level
	 */
	public int getNestingLevel();

	/**
	 * Sets the offset.
	 *
	 * @param offset the new offset
	 */
	public void setOffset(int offset);

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset();

	/**
	 * Gets all IDS in a single Map.
	 *
	 * @return a map containing all ids
	 */
	public Map<String, T> getAllIDs();

	/**
	 * Gets all functions.
	 *
	 * @return the LinkedList containing all the scopes filled with functions only
	 */
	public LinkedList<HashMap<String, T>> getAllFunctions();

	/**
	 * Clone.
	 *
	 * @return the cloned object
	 */
	@Override
	public Object clone();

	/**
	 * Equals.
	 *
	 * @param obj the object to compare with
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj);
}
