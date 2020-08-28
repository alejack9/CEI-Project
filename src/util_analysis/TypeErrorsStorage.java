/*
 * 
 */
package util_analysis;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeErrorsStorage.
 */
public class TypeErrorsStorage {

	/** The storage. */
	private static List<TypeError> storage = new LinkedList<TypeError>();

	/**
	 * Instantiates a new type errors storage.
	 */
	private TypeErrorsStorage() {
	}

	/**
	 * Adds the error.
	 *
	 * @param e the e
	 */
	public static void addError(TypeError e) {
		storage.add(e);
	}

	/**
	 * Gets the type errors.
	 *
	 * @return the type errors
	 */
	public static List<TypeError> getTypeErrors() {
		return storage;
	}

	/**
	 * Clear.
	 */
	public static void clear() {
		storage.clear();
	}
}
