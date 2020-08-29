/*
 * 
 */
package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// TODO: Auto-generated Javadoc
/**
 * Specified class that writes on a target file.
 */
class FileOutputLogger extends Logger {

	/**
	 * Instantiates a new file output logger.
	 *
	 * @param file The file in which write
	 * @throws FileNotFoundException the file not found exception
	 */
	public FileOutputLogger(File file) throws FileNotFoundException {
		// generating a PrintStream from a generated FileOutputStream based on passed
		// file
		super(new PrintStream(new FileOutputStream(file)));
	}
}
