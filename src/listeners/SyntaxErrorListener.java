package listeners;

import java.io.IOException;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import logger.Logger;

/**
 * A syntax error listener.
 */
public class SyntaxErrorListener implements ANTLRErrorListener {

	public final Logger logger;
	private boolean errors = false;

	/**
	 * @param logger The logger in which write
	 */
	public SyntaxErrorListener(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		try {
			if (logger != null)
				logger.writeLine("SYNTAX ERROR: " + "[ " + line + ":" + charPositionInLine + " ] - " + msg);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		errors = true;
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

	public boolean errorsDetected() {
		return errors;
	}

}
