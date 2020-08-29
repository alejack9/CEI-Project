package listeners;

import java.io.IOException;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import logger.Logger;

/**
 * Specific listener for parser
 */
public class SimpleSintaxErrorListener extends SimpleErrorListener {

	/**
	 * @param logger - The logger in which write
	 */
	public SimpleSintaxErrorListener(Logger logger) {
		super(logger);
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		try {
			logger.write("SYNTAX ERROR: ");
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
	}
}
