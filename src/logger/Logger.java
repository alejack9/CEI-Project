package logger;

import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;

/**
 * Default logger: handles print outputs;
 */
public abstract class Logger {

	protected PrintStream out;

	/**
	 * @param out
	 */
	protected Logger(PrintStream out) {
		this.out = out;
	}


	/**
	 * Writes a new line through the PrintStream
	 * @param message The message to print
	 * @throws IOException
	 */
	public void writeLine(String message) throws IOException {
		this.out.println(message);
	};

	/**
	 * Writes a string through the PrintStream
	 * @param message The message to print
	 * @throws IOException
	 */
	public void write(String message) throws IOException {
		this.out.print(message);
	}

	/**
	 * Writes a new line through the PrintStream
	 * @throws IOException
	 */
	public void writeLine() throws IOException {
		this.out.println();
	}

}
