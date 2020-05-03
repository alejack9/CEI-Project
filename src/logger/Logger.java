package logger;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Default logger: handles print outputs;
 */
public abstract class Logger {

	private PrintStream out;

	/**
	 * Not instantiable
	 * @param out OutputPrinter (used to print the output)
	 */
	protected Logger(PrintStream out) {
		this.out = out;
	}

	public void writeLine(String message) throws IOException {
		this.write(message + "\r\n");
	};

	public void write(String message) throws IOException {
		this.out.print(message);
	}

	public void writeLine() throws IOException {
		this.writeLine("");
	};
}
