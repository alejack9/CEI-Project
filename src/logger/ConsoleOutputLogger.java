package logger;

/**
 * Specified logger that writes on console
 */
class ConsoleOutputLogger extends Logger {

	protected ConsoleOutputLogger() {
		// passing to superclass the System.out PrintStream
		this(true);
	}
	
	protected ConsoleOutputLogger(boolean verbose) {
		// passing to superclass the System.out PrintStream
		super(System.out, verbose);
	}
}
