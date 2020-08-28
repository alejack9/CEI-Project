package main;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * Functional interface for step.
 */
@FunctionalInterface
public interface Step {
	
	/**
	 * Gets the.
	 *
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean get() throws IOException;
}
