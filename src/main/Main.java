package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import ast.StmtBlock;
import ast.VisitorImplSP;
import ast.VisitorImplSVM;
import ast.errors.BehaviourError;
import ast.errors.LexicalError;
import ast.errors.SemanticError;
import ast.errors.TypeError;
import listeners.SyntaxErrorListener;
import logger.Logger;
import logger.LoggerFactory;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

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
 * The main class.
 */
public class Main {

	private Logger logger;
	private String inFileName = "test.spl";
	private String outCodeFileName;

	private StmtBlock mainBlock;
	private SimplePlusLexer lexer;

	private SyntaxErrorListener parserErrorsListener;

	/**
	 * The step that performs lexical check.
	 */
	private Step checkLexicalStep = () -> {
		logger.write("Checking Lexical ... ");

		if (lexer.errors.size() > 0) {
			logger.writeLineWithoutDateTime("failed");
			for (LexicalError error : lexer.errors)
				logger.writeLine("Lexical Error: " + error.toString());
			return false;
		}

		logger.writeLineWithoutDateTime("succeeded");

		return true;
	};

	/**
	 * The step that performs semantic check.
	 */
	private Step checkSemanticStep = () -> {
		logger.write("Checking Semantic ... ");

		List<SemanticError> errors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());

		if (errors.size() > 0) {
			logger.writeLineWithoutDateTime("failed");
			for (SemanticError err : errors)
				logger.writeLine(err.toString());
			return false;
		}

		logger.writeLineWithoutDateTime("succeeded");
		return true;
	};

	/**
	 * The step that performs type check.
	 */
	private Step checkTypesStep = () -> {
		logger.write("Checking Types ... ");

		mainBlock.inferType();

		if (TypeErrorsStorage.getTypeErrors().size() > 0) {
			logger.writeLineWithoutDateTime("failed");
			for (TypeError err : TypeErrorsStorage.getTypeErrors())
				logger.writeLine(err.toString());
			return false;
		}

		logger.writeLineWithoutDateTime("succeeded");
		return true;
	};

	/**
	 * The step that performs behaviour analysis.
	 */
	private Step analyseBehaviourStep = () -> {
		List<BehaviourError> bErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());

		logger.write("Analysing Behaviour ... ");

		if (bErrors.size() > 0) {
			logger.writeLineWithoutDateTime("failed");
			for (SemanticError bErr : bErrors)
				logger.writeLine(bErr.toString());
			return false;
		}

		logger.writeLineWithoutDateTime("succeeded");
		return true;
	};

	/**
	 * The step that performs the code generation.
	 */
	private Step generateCodeStep = () -> {
		logger.write("Generating Code ... ");

		generateOutCode(new CodeMaker(mainBlock).codeGen());

		logger.writeLineWithoutDateTime("done");
		return true;
	};

	/**
	 * The step that runs the generated code.
	 */
	private Step runCodeStep = () -> {
		logger.writeLine("Lanching program (Following output)");
		logger.writeLine();

		SVMParser SVMparser = new SVMParser(
				new CommonTokenStream(new SVMLexer(new ANTLRInputStream(new FileInputStream(outCodeFileName)))));

		SVMparser.setBuildParseTree(true);

		VisitorImplSVM SVMVisitor = new VisitorImplSVM();

		SVMVisitor.visitAssembly(SVMparser.assembly());

		new ExecuteVM(SVMVisitor.getCode()).cpu();

		return true;
	};

	/**
	 * List of substitute suppliers.
	 */
	private List<Step> steps = new LinkedList<Step>();

	private Main(Logger logger) {
		this.logger = logger;
		this.steps.add(checkLexicalStep);
		this.steps.add(checkSemanticStep);
		this.steps.add(checkTypesStep);
		this.steps.add(analyseBehaviourStep);
		this.steps.add(generateCodeStep);
		this.steps.add(runCodeStep);
	}

	public static void main(String[] args) throws IOException {
		new Main(LoggerFactory.getLogger()).start(args);
	}

	/**
	 * Compile a SimplePlus program, generate code and execute the generated code.
	 *
	 * @param args the args.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void start(String[] args) throws IOException {
		manipulateArgs(args);

		ANTLRInputStream input = null;

		try {
			input = new ANTLRInputStream(new FileInputStream(inFileName));
		} catch(FileNotFoundException e) {
			System.out.println("Input file '%s' not found".replace("%s", inFileName));
			quit();
		}

		logger.writeLine("Input File Name: " + inFileName);
		logger.writeLine("Code File Name: " + outCodeFileName);
		logger.writeLine();

		lexer = new SimplePlusLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		SimplePlusParser parser = new SimplePlusParser(tokens);

		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		parserErrorsListener = new SyntaxErrorListener(logger);
		parser.addErrorListener(parserErrorsListener);

		// Tell the parser to build the AST
		parser.setBuildParseTree(true);

		VisitorImplSP visitor = new VisitorImplSP();

		try {
			mainBlock = (StmtBlock) visitor.visitBlock(parser.block());
		} catch (Exception e) {
			if (!parserErrorsListener.errorsDetected())
				throw e;
		} finally {
			if (parserErrorsListener.errorsDetected())
				quit();
		}

		// Execute each step; if any step return false, the processor is killed
		for (Step step : steps)
			if (!step.get())
				quit();
		// same as the above but without "quitting" message
//		int i = 0;
//		while(steps.get(i++).get());

	}

	/**
	 * Handle parameters of the terminal command for execution of .jar file.
	 * 
	 * @param args the arguments of the terminal command.
	 */
	private void manipulateArgs(String[] args) {
		switch (args.length) {
		case 0:
			break;
		case 1:
			inFileName = args[0];
			break;
		default:
			System.out.println("Usage: \"java -jar .\\exportedJar.jar input_file\"");
		}

		// Set outCodeFileName with the same name of the input file but different
		// extension
		outCodeFileName = inFileName.replaceFirst("[.][^.]+$", "") + ".out";
	}

	/**
	 * Write the generated code on file.
	 *
	 * @param code the generated code.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void generateOutCode(String code) throws IOException {
		LoggerFactory.getLogger(outCodeFileName).write(code.replaceFirst("\r\n", ""));
	}

	/**
	 * Stop the program build.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void quit() throws IOException {
		logger.writeLine("Quitting compiling");
		System.exit(-1);
	}
}
