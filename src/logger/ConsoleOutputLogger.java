package logger;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Specific logger that writes on console.
 */
class ConsoleOutputLogger extends Logger {

	/**
	 * Instantiate a new console output logger.
	 */
	protected ConsoleOutputLogger() {
		// passing to superclass the System.out PrintStream
		super(System.out);
	}

	/**
	 * Write a new line including DateTime
	 * 
	 * @param message the message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void writeLine(String message) throws IOException {
		this.out.println(LocalDateTime.now().toString() + " - " + message);
	};

	/**
	 * Write a new line including DateTime if specified
	 * 
	 * @param message      the message
	 * @param hideDateTime true if the date time has not to be shown
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void writeLine(String message, boolean hideDateTime) throws IOException {
		this.out.println((hideDateTime ? "" : (LocalDateTime.now().toString() + " - ")) + message);
	};

	/**
	 * Write without start a new line, including DateTime
	 * 
	 * @param message The message to print
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void write(String message) throws IOException {
		this.out.print(LocalDateTime.now().toString() + " - " + message);
	}
}
