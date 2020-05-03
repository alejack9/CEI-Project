package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoggerFactory {
	private static Logger consoleLogger;
	private static Map<String, Logger> filesLoggers = new HashMap<String, Logger>();

	private LoggerFactory() {
	}

	public static Logger getLogger() {
		if (consoleLogger == null)
			consoleLogger = new ConsoleOutputLogger();
		return consoleLogger;
	}

	public static Logger getLogger(String fileName) throws IOException{
		if (!filesLoggers.containsKey(fileName)) {
			File file = new File(fileName);
			file.createNewFile();
			try {
				filesLoggers.put(fileName, new FileOutputLogger(file));
			} catch (FileNotFoundException e) {
				// this will never happen
			}
			}
		return filesLoggers.get(fileName);
	}

}
