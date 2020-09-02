package main;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * Functional interface for step. Variant of {@link Supplier Supplier} that
 * allows to throw exceptions. <br>
 */
@FunctionalInterface
public interface Step {
	/**
	 * @return true if there has been no errors, false otherwise
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean get() throws IOException;
}
