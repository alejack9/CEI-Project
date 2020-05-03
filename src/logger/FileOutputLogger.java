package logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class FileOutputLogger extends Logger {

	public FileOutputLogger(File file) throws FileNotFoundException {
		super(new PrintStream(new FileOutputStream(file)));
	}
}
