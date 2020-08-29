/*
 * 
 */
package util_analysis;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;

/**
 * The Storage of Type Errors.
 */
public class TypeErrorsStorage {

	/** The storage. */
	private static List<TypeError> storage = new LinkedList<TypeError>();

	private TypeErrorsStorage() {
	}

	/**
	 * Adds a type error.
	 *
	 * @param e the e
	 */
	public static void addError(TypeError e) {
		storage.add(e);
	}

	/**
	 * Gets the stored errors.
	 *
	 * @return the type errors
	 */
	public static List<TypeError> getTypeErrors() {
		return storage;
	}

	/**
	 * Clears the storage buffer.
	 */
	public static void clear() {
		storage.clear();
	}
}
