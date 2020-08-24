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

public class Analyse {
	
	private final Logger logger;
	private String inFileName = "test.spl";
	private String errorsFileName = null;
	private String outCodeFileName;

	private Analyse(Logger logger) {
		this.logger = logger;
	}

	public static void main(String[] args) throws IOException {
		new Analyse(LoggerFactory.getLogger()).start(args);
	}
	
	private void start(String[] args) throws IOException {
		manipulateArgs(args);
		FileInputStream is = new FileInputStream(inFileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
	
		logger.writeLine("Input File Name: " + inFileName);
		logger.writeLine("Code File Name: " + outCodeFileName);
	
		logger.writeLine();
		
		SimplePlusLexer lexer = new SimplePlusLexer(input);
		// disable default ANTLR lexer listener (to override default behavior)
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		SimpleErrorListener sl = null;
		if(errorsFileName != null) {
			logger.writeLine("Errors File Name: " + errorsFileName);
			 sl = new SimpleSintaxErrorListener(LoggerFactory.getLogger(errorsFileName));
			lexer.addErrorListener(sl);
		}
		lexer.addErrorListener(new SimpleLexerErrorListener(logger));
	
		logger.writeLine("Creating Tokens Stream...");
	
		//create parser
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		logger.writeLine("...Tokens Stream Created");
	
		logger.writeLine();
		
		SimplePlusParser parser = new SimplePlusParser(tokens);
	
		// disable default ANTLR syntax listener (to override default behavior)
		parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
		if(errorsFileName != null)
			parser.addErrorListener(sl);
		parser.addErrorListener(new SimpleSintaxErrorListener(logger));
	
		//tell the parser to build the AST
		parser.setBuildParseTree(true);
		
		//build custom visitor
		VisitorImplSP visitor = new VisitorImplSP();
		
		//visit the root, this will recursively visit the whole tree
		StmtBlock mainBlock = (StmtBlock) visitor.visitBlock(parser.block());
		
		if(lexer.errors.size() > 0) {
			for (LexicalError error : lexer.errors)
				logger.writeLine("Lexical Error: " + error.toString());
			quit(logger);
		}
		
		if (sl != null && sl.errorsDetected())
			quit(logger);
	
		logger.writeLine("Checking semantic...");
		
		List<SemanticError> errors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());
		
		if (errors.size() > 0) {
			logger.writeLine("Check semantics FAILED");
			for (SemanticError err : errors)
				logger.writeLine(err.toString());
			quit(logger);
		} 
		
		logger.writeLine("Check semantic succeeded");
	
		logger.writeLine();
		
		logger.writeLine("Checking types...");
			
		mainBlock.inferType();
		
		if (TypeErrorsStorage.getTypeErrors().size() > 0) {
			logger.writeLine("Type check FAILED");
			for (TypeError err : TypeErrorsStorage.getTypeErrors())
				logger.writeLine(err.toString());
			quit(logger);
		} 
	
		logger.writeLine("Type check succeeded");
	
		logger.writeLine();
		
		List<BehaviourError> bErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());
		
		if (bErrors.size() > 0) {
			logger.writeLine("Behavioural analysis FAILED");			
			for(SemanticError bErr: bErrors)
				logger.writeLine(bErr.toString());
			quit(logger);
		}
		logger.writeLine("Behavioural analysis succeeded");
		
		generateOutCode(mainBlock.codeGen());
	
		runCode();
	}

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
	
	private void generateOutCode(String code) throws IOException {
		LoggerFactory.getLogger(outCodeFileName).write(code.replaceFirst("\r\n", ""));
	}
	
	private void runCode() throws IOException {
		SVMParser SVMparser = new SVMParser(
				new CommonTokenStream(
						new SVMLexer(
								new ANTLRInputStream(
										new FileInputStream(outCodeFileName)
		))));
		
		SVMparser.setBuildParseTree(true);
		
		VisitorImplSVM SVMVisitor = new VisitorImplSVM();
		
		SVMVisitor.visitAssembly(SVMparser.assembly());
		
		new ExecuteVM(SVMVisitor.getCode()).cpu();
	}

	private void quit(Logger clogger) throws IOException {
		clogger.writeLine("Quitting compiling");
		System.exit(-1);
	}
}
