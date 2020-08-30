package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

import ast.StmtBlock;
import ast.VisitorImplSP;
import ast.VisitorImplSVM;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import listeners.ParseErrorListener;
import parser.ExecuteVM;
import parser.SVMLexer;
import parser.SVMParser;
import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import support.CodeMaker;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

/**
 * The Class useful for testing routines.
 */
class Utils {

	private Utils() {
	}

	private static SimplePlusLexer lexer = null;
	private static StmtBlock mainBlock = null;

	public static void parseErrors(String code, boolean errors) throws IOException {
		lexer = new SimplePlusLexer(new ANTLRInputStream(new ByteArrayInputStream(code.getBytes())));

		SimplePlusParser parser = new SimplePlusParser(new CommonTokenStream(lexer));
		ParseErrorListener parseErrorListener = new ParseErrorListener(null);
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parser.addErrorListener(parseErrorListener);

		parser.setBuildParseTree(true);

		VisitorImplSP visitor = new VisitorImplSP();

		try {
			mainBlock = (StmtBlock) visitor.visitBlock(parser.block());
		} catch (Exception e) {
			mainBlock = null;
		}

		assertTrue(parseErrorListener.errorsDetected() == errors);
	}

	/**
	 * Assert that the lexical errors counted are the same as the passed number.
	 *
	 * @param code   the code
	 * @param number the expected number of errors
	 * @return the main block
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void lexicalErrors(String code, int number) throws IOException {
		parseErrors(code, false);

		assertEquals(number, lexer.errors.size());
	}

	/**
	 * Assert that the semantic errors counted are the same as the passed number.
	 * 
	 * @param code   the code
	 * @param number the expected number of errors
	 * @return the main block
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void semanticErrors(String code, int number) throws IOException {
		lexicalErrors(code, 0);

		List<SemanticError> semanticErrors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());

		assertEquals(number, semanticErrors.size());
	}

	/**
	 * Assert that the type errors counted are the same as the passed number.
	 * 
	 * @param code   the code
	 * @param number the expected number of errors
	 * @return the main block
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void typeErrors(String code, int number) throws IOException {
		semanticErrors(code, 0);

		mainBlock.inferType();

		assertEquals(number, TypeErrorsStorage.getTypeErrors().size());

	}

	/**
	 * Assert that the behaviour errors counted are the same as the passed number.
	 * 
	 * @param code   the code
	 * @param number the expected number of errors
	 * @return the main block
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void behaviourErrors(String code, int number) throws IOException {
		typeErrors(code, 0);
		List<BehaviourError> behaviourErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());

		assertEquals(number, behaviourErrors.size());
	}

	/**
	 * Run the passed code.
	 *
	 * @param code the code
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void runCode(String code) throws IOException {
		SVMParser SVMparser = new SVMParser(new CommonTokenStream(new SVMLexer(
				new ANTLRInputStream(new ByteArrayInputStream(code.replaceFirst("\r\n", "").getBytes())))));

		SVMparser.setBuildParseTree(true);

		VisitorImplSVM SVMVisitor = new VisitorImplSVM();

		SVMVisitor.visitAssembly(SVMparser.assembly());

		assertDoesNotThrow(() -> new ExecuteVM(SVMVisitor.getCode()).cpu());
	}

	/**
	 * Assert that there are no errors in the passed code and run it
	 *
	 * @param code the code
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void correctCode(String code) throws IOException {
		behaviourErrors(code, 0);
		runCode(new CodeMaker(mainBlock).codeGen());
	}

}
