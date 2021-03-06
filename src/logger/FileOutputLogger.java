package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Specific logger that writes on a target file.
 */
class FileOutputLogger extends Logger {

	/**
	 * Instantiate a new file output logger.
	 *
	 * @param file The file in which write
	 * @throws FileNotFoundException the file not found exception
	 */
	public FileOutputLogger(File file) throws FileNotFoundException {
		super(new PrintStream(new FileOutputStream(file)));
	}
}
