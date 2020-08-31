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
 * A generic error listener.
 */
public abstract class CustomErrorListener implements ANTLRErrorListener {

	protected final Logger logger;
	private boolean errors = false;

	/**
	 * This class can be instanced by children.
	 * 
	 * @param logger The logger in which write
	 */
	protected CustomErrorListener(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		try {
			if (logger != null)
				this.logger.writeLine("line " + line + ":" + charPositionInLine + "\t" + msg);
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