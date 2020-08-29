/*
 * 
 */
package logger;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Default logger: handles print outputs.
 */
public abstract class Logger {

	protected PrintStream out;

	/**
	 * Instantiates a new logger.
	 *
	 * @param out the {@link PrintStream PrintStream} class used to write
	 */
	protected Logger(PrintStream out) {
		this.out = out;
	}

	/**
	 * Writes a new line.
	 *
	 * @param message The message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine(String message) throws IOException {
		this.writeLine(message, false);
	};

	/**
	 * Writes a new line without any message.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine() throws IOException {
		this.out.println();
	}

	/**
	 * Writes a message.
	 *
	 * @param message      The message to print
	 * @param hideDateTime the hide date time
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeLine(String message, boolean hideDateTime) throws IOException {
		this.out.println(message);
	}

	/**
	 * Writes a message.
	 *
	 * @param message The message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void write(String message) throws IOException {
		this.out.print(message);
	}

}
