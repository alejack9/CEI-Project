package main;

import java.io.IOException;

/**
 * Functional interface for step. Variant of Supplier that allows to generate exceptions.
 */
@FunctionalInterface
public interface Step {
	/**
	 * Perform the relative check implemented from interface instance.
	 *
	 * @return boolean - true if there is not errors, false otherwise
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean get() throws IOException;
}
