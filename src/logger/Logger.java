package logger;

import java.io.IOException;
import java.io.PrintStream;

public abstract class Logger {

	private PrintStream out;

	protected Logger(PrintStream out) {
		this.out = out;
	}

	public void writeLine(String message) throws IOException {
		this.write(message + "\r\n");
	};

	public void write(String message) throws IOException {
		this.out.print(message);
	};
}
