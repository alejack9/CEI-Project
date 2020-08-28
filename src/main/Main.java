package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import ast.StmtBlock;
import ast.VisitorImplSP;
import ast.VisitorImplSVM;
import ast.errors.BehaviourError;
import ast.errors.LexicalError;
import ast.errors.SemanticError;
import ast.errors.TypeError;
import listeners.SimpleErrorListener;
import listeners.SimpleLexerErrorListener;
import listeners.SimpleSintaxErrorListener;
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
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

public class Main {

	private final Logger logger;
	private String inFileName = "test.spl";
	private String errorsFileName = null;
	private String outCodeFileName;

	private Main(Logger logger) {
		this.logger = logger;
	}

	public static void main(String[] args) throws IOException {
		new Main(LoggerFactory.getLogger()).start(args);
	}

	/**
	 * Compile a SimplePlus program, generate code and execute the code
	 * @param args
	 * @throws IOException
	 */
	private void start(String[] args) throws IOException {
		manipulateArgs(args);
		ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(inFileName));

		logger.writeLine("Input File Name: " + inFileName);
		logger.writeLine("Code File Name: " + outCodeFileName);

		logger.writeLine();

		// Create Lexer
		SimplePlusLexer lexer = new SimplePlusLexer(input);

		// Disable default ANTLR lexer listener (to override default behavior)
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		SimpleErrorListener sl = null;
		
		// Check if was specified the parameter for the lexical errors file name
		if (errorsFileName != null) {
			logger.writeLine("Errors File Name: " + errorsFileName);
			sl = new SimpleSintaxErrorListener(LoggerFactory.getLogger(errorsFileName));
			lexer.addErrorListener(sl);
		}
		lexer.addErrorListener(new SimpleLexerErrorListener(logger));

		// Create parser
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		SimplePlusParser parser = new SimplePlusParser(tokens);

		// Disable default ANTLR syntax listener (to override default behavior)
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		
		// Check if was specified the parameter for the syntax errors file name
		if (errorsFileName != null)
			parser.addErrorListener(sl);
		parser.addErrorListener(new SimpleSintaxErrorListener(logger));

		// Tell the parser to build the AST
		parser.setBuildParseTree(true);

		// Build custom visitor
		VisitorImplSP visitor = new VisitorImplSP();

		// Visit the root, this will recursively visit the whole tree
		StmtBlock mainBlock = (StmtBlock) visitor.visitBlock(parser.block());

		logger.write("Checking Lexical ... ");

		
		if (lexer.errors.size() > 0) {
			logger.writeLine("failed", true);
			for (LexicalError error : lexer.errors)
				logger.writeLine("Lexical Error: " + error.toString());
			quit(logger);
		}

		logger.writeLine("succeeded", true);

		// Stop the execution if there is lexical error
		if (sl != null && sl.errorsDetected())
			quit(logger);

		logger.write("Checking Semantic ... ");

		// Check semantics
		List<SemanticError> errors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());

		// Stop the execution if there is semantic error
		if (errors.size() > 0) {
			logger.writeLine("failed", true);
			for (SemanticError err : errors)
				logger.writeLine(err.toString());
			quit(logger);
		}

		logger.writeLine("succeeded", true);

		logger.write("Checking Types ... ");

		// Check types
		mainBlock.inferType();

		// Stop the execution if there is types error
		if (TypeErrorsStorage.getTypeErrors().size() > 0) {
			logger.writeLine("failed", true);
			for (TypeError err : TypeErrorsStorage.getTypeErrors())
				logger.writeLine(err.toString());
			quit(logger);
		}

		logger.writeLine("succeeded", true);

		// Bheaviour analysis
		List<BehaviourError> bErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());

		logger.write("Analysing Behaviour ... ");

		// Stop the execution if there is behavioural error
		if (bErrors.size() > 0) {
			logger.writeLine("failed", true);
			for (SemanticError bErr : bErrors)
				logger.writeLine(bErr.toString());
			quit(logger);
		}

		logger.writeLine("succeeded", true);

		logger.write("Generating Code ... ");

		// Code generation
		generateOutCode(mainBlock.codeGen());

		logger.writeLine("done", true);

		logger.writeLine("Launching program (Following output)");
		
		logger.writeLine();

		// Code execution
		runCode();
	}

	/*
	 * Set inFileName or/and errorsFileName if were specified
	 */
	private void manipulateArgs(String[] args) {
		switch (args.length) {
		case 0:
			break;
		case 1:
			inFileName = args[0];
			break;
		case 2:
			inFileName = args[0];
			errorsFileName = args[1];
			break;
		default:
			System.out.println("Usage: \"java -jar .\\exportedJar.jar input_file [errors_file]\"");
		}
		outCodeFileName = inFileName.replaceFirst("[.][^.]+$", "") + ".out";
	}

	/*
	 * Write the generated code on file
	 */
	private void generateOutCode(String code) throws IOException {
		LoggerFactory.getLogger(outCodeFileName).write(code.replaceFirst("\r\n", ""));
	}

	/**
	 * Run the generated code
	 * @throws IOException
	 */
	private void runCode() throws IOException {
		// Create parser for SVM
		SVMParser SVMparser = new SVMParser(
				new CommonTokenStream(new SVMLexer(new ANTLRInputStream(new FileInputStream(outCodeFileName)))));

		// Tell the parser to build the AST for SVM
		SVMparser.setBuildParseTree(true);

		// Build custom visitor
		VisitorImplSVM SVMVisitor = new VisitorImplSVM();

		SVMVisitor.visitAssembly(SVMparser.assembly());

		// Run the code
		new ExecuteVM(SVMVisitor.getCode()).cpu();
	}

	// Stop the execution
	private void quit(Logger clogger) throws IOException {
		clogger.writeLine("Quitting compiling");
		System.exit(-1);
	}
}
