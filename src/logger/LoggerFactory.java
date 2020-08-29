package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory that generates Loggers.
 */
public class LoggerFactory {

	/** The unique instance of the logger that writes on console. */
	private static Logger consoleLogger;

	/**
	 * A {@link Map Map} containing all associations &lt;fileName,Logger&gt;: a
	 * specific file is associated with one and only one logger
	 */
	private static Map<String, Logger> filesLoggers = new HashMap<String, Logger>();

	private LoggerFactory() {
	}

	/**
	 * Get the console logger.
	 *
	 * @return A unique logger
	 */
	public static Logger getLogger() {
		if (consoleLogger == null)
			consoleLogger = new ConsoleOutputLogger();
		return consoleLogger;
	}

	/**
	 * Get the logger associated with the passed fileName, if there's no loggers
	 * associated with, it creates a new instance.
	 *
	 * @param fileName the file name
	 * @return A unique logger that writes on the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Logger getLogger(String fileName) throws IOException {
		if (!filesLoggers.containsKey(fileName)) {
			File file = new File(fileName);
			file.createNewFile();

			try {
				filesLoggers.put(fileName, new FileOutputLogger(file));
			}
			// this will never happen
			catch (FileNotFoundException e) {
			}
		}
		return filesLoggers.get(fileName);
	}
}
