package listeners;

import java.io.IOException;
import java.util.BitSet;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import logger.Logger;

/**
 * Specific listener for lexer
 */
public class SimpleLexerErrorListener extends SimpleErrorListener {

	/**
	 * @param logger The logger in which write
	 */
	public SimpleLexerErrorListener(Logger logger) {
		super(logger);
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		try {
			logger.write("LEXICAL ERROR: ");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

	}

	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {

	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {

	}

	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {

	}

}