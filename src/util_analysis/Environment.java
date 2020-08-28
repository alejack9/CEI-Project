/*
 * 
 */
package util_analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import support.MyCloneable;
import util_analysis.entries.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Interface Environment.
 *
 * @param <T> the generic type
 */
public interface Environment<T extends Entry> extends MyCloneable {
	
	/**
	 * Adds the.
	 *
	 * @param id the id
	 * @param entry the entry
	 * @return true, if successful
	 */
	public boolean add(String id, T entry);

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param entry the entry
	 * @return the t
	 */
	public T update(String id, T entry);

	/**
	 * Open scope.
	 */
	public void openScope();

	/**
	 * Close scope.
	 */
	public void closeScope();

	/**
	 * Delete variable.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T deleteVariable(String id);

	/**
	 * Gets the ID entry.
	 *
	 * @param id the id
	 * @return the ID entry
	 */
	public T getIDEntry(String id);

	/**
	 * Gets the local ID entry.
	 *
	 * @param id the id
	 * @return the local ID entry
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
	 * Gets the all I ds.
	 *
	 * @return the all I ds
	 */
	public Map<String, T> getAllIDs();

	/**
	 * Gets the all functions.
	 *
	 * @return the all functions
	 */
	public LinkedList<HashMap<String, T>> getAllFunctions();

	/**
	 * Clone.
	 *
	 * @return the object
	 */
	@Override
	public Object clone();

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj);
}
