/*
 * 
 */
package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * Factory that generates Loggers.
 */
public class LoggerFactory {
	
	/** the unique instance of the logger that writes on console. */
	private static Logger consoleLogger;

	/** a Map containing entries with <fileName,Logger> pattern: contains loggers that writes on a specified file. */
	private static Map<String, Logger> filesLoggers = new HashMap<String, Logger>();

	/**
	 * Instantiates a new logger factory.
	 */
	private LoggerFactory() {
	}

	/**
	 * Gets the logger.
	 *
	 * @return A unique logger
	 */
	public static Logger getLogger() {
		if (consoleLogger == null)
			consoleLogger = new ConsoleOutputLogger();
		return consoleLogger;
	}

	/**
	 * Gets the logger.
	 *
	 * @param fileName the file name
	 * @return A unique logger that writes on the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Logger getLogger(String fileName) throws IOException {
		// if the file is not registered
		if (!filesLoggers.containsKey(fileName)) {
			// create a new file
			File file = new File(fileName);
			file.createNewFile();

			try {
				filesLoggers.put(fileName, new FileOutputLogger(file));
			}
			// this will never happen
			catch (FileNotFoundException e) {
			}
		}
		// there's always a logger
		return filesLoggers.get(fileName);
	}
}
