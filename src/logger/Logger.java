package logger;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Default logger: handles print outputs;
 */
public abstract class Logger {

	protected PrintStream out;
	protected boolean verbose;

	/**
	 * Not instantiable
	 * @param out OutputPrinter (used to print the output)
	 */
	protected Logger(PrintStream out) {
		this(out, true);
	}
	
	/**
	 * @param out
	 * @param verbose
	 */
	protected Logger(PrintStream out, boolean verbose) {
		this.out = out;
		this.verbose = verbose;
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

	public boolean isVerbose() {
		return this.verbose;
	};
}
