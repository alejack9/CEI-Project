package logger;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Specified logger that writes on console
 */
class ConsoleOutputLogger extends Logger {
	
	protected ConsoleOutputLogger() {
		// passing to superclass the System.out PrintStream
		super(System.out);
	}

	/**
	 * Writes a new line through the PrintStream
	 * @param message The message to print
	 * @throws IOException
	 */
	public void writeLine(String message) throws IOException {
		this.out.println(LocalDateTime.now().toString() + " - " + message);
	};

	/**
	 * Writes a string through the PrintStream
	 * @param message The message to print
	 * @throws IOException
	 */
	public void write(String message) throws IOException {
		this.out.print(LocalDateTime.now().toString() + " - " + message);
	} 
}
