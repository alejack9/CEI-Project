package util_analysis;

import java.util.LinkedList;
import java.util.List;

import ast.errors.TypeError;

/**
 * The Storage of Type Errors.
 */
public class TypeErrorsStorage {
	private static List<TypeError> storage = new LinkedList<TypeError>();

	private TypeErrorsStorage() {
	}

	/**
	 * Add a type error.
	 *
	 * @param typeError the type error
	 */
	public static void addError(TypeError typeError) {
		storage.add(typeError);
	}

	/**
	 * Get the stored errors.
	 *
	 * @return the type errors
	 */
	public static List<TypeError> getTypeErrors() {
		return storage;
	}

	/**
	 * Clear the storage buffer.
	 */
	public static void clear() {
		storage.clear();
	}
}
