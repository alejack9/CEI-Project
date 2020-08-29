/*
 * 
 */
package logger;

import java.io.IOException;
import java.io.PrintStream;

// TODO: Auto-generated Javadoc
/**
 * Default logger: handles print outputs;.
 */
public abstract class Logger {

	/** The out. */
	protected PrintStream out;

	/**
	 * Instantiates a new logger.
	 *
	 * @param out the out
	 */
	protected Logger(PrintStream out) {
		this.out = out;
	}

	/**
	 * Writes a new line through the PrintStream.
	 *
	 * @param message The message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine(String message) throws IOException {
		this.writeLine(message, false);
	};

	/**
	 * Writes a string through the PrintStream.
	 *
	 * @param message The message to print
	 * @param hideDateTime the hide date time
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine(String message, boolean hideDateTime) throws IOException {
		this.out.println(message);
	}
	
	/**
	 * Writes a string through the PrintStream.
	 *
	 * @param message The message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(String message) throws IOException {
		this.out.print(message);
	}

	/**
	 * Writes a new line through the PrintStream.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine() throws IOException {
		this.out.println();
	}

}
