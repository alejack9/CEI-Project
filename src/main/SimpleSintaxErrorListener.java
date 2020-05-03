package main;

import java.io.IOException;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import logger.Logger;

public class SimpleSintaxErrorListener extends SimpleErrorListener {

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
