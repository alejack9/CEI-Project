package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

import ast.ElementBase;
import ast.VisitorImpl;
import ast.errors.SemanticError;
import ast.errors.SemanticErrorType;
import logger.Logger;
import logger.LoggerFactory;
import parser.SimpleLexer;
import parser.SimpleParser;
import util_analysis.Environment;

public class Analyse {

	public static void main(String[] args) {
		String inFileName = "test.spl";
		String outFileName = "errors.txt";

		switch (args.length) {
		case 0:
			break;
		case 1:
			inFileName = args[0];
			break;
		case 2:
			inFileName = args[0];
			outFileName = args[1];
			break;
		default:
			System.out.println("Usage: \"java -jar .\\exportedJar.jar inputfile outputfile\"");
		}

		// create console logger
		Logger clogger = LoggerFactory.getLogger(false);
		try {
			FileInputStream is = new FileInputStream(inFileName);
			ANTLRInputStream input = new ANTLRInputStream(is);

			clogger.writeLine("Input File: " + inFileName);
			clogger.writeLine("Output File: " + outFileName);

			clogger.writeLine();

			SimpleLexer lexer = new SimpleLexer(input);
			// disable default ANTLR lexer listener (to override default behavior)
			lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
			lexer.addErrorListener(new SimpleLexerErrorListener(LoggerFactory.getLogger(outFileName, false)));
			lexer.addErrorListener(new SimpleLexerErrorListener(clogger));

			clogger.writeLine("Collecting Tokens...");

			CommonTokenStream tokens = new CommonTokenStream(lexer);

			clogger.writeLine("...Tokens collected");

			clogger.writeLine();

			SimpleParser parser = new SimpleParser(tokens);
			// disable default ANTLR syntax listener (to override default behavior)
			parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
			// logger returned from LoggerFactory writes on errors.txt file
			parser.addErrorListener(new SimpleSintaxErrorListener(LoggerFactory.getLogger(outFileName, false)));
			parser.addErrorListener(new SimpleSintaxErrorListener(clogger));

			parser.setBuildParseTree(true);

			VisitorImpl visitor = new VisitorImpl();

			clogger.writeLine("Creating AST (lexer and parser analysis)...");

			ElementBase mainBlock = visitor.visitBlock(parser.block());

			clogger.writeLine("...AST created (lexer and parser analysis complete)");

			clogger.writeLine();

			clogger.writeLine("Checking semantic...");

			// fill errors list with semantics errors
			List<SemanticError> errors = mainBlock.checkSemantics(new Environment());

			if (errors.size() > 0) {
				clogger.writeLine("Check semantics FAILED");
				for (SemanticError err : errors)
					clogger.writeLine(err.toString());
			}

			clogger.writeLine("...Semantic check done");

			clogger.writeLine();

			/*
			 * EXERCISE 3: check if duplicated ID error exists in semantic errors list
			 * (IDALREADYEXISTS to check function's ID duplicates)
			 */
			clogger.writeLine("Same ID in the same block:");
			clogger.writeLine("\t" + errors.stream().anyMatch(s -> s.getType() == SemanticErrorType.IDALREADYEXISTS));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			try {
				clogger.writeLine("Error while semantic checking");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (clogger.isVerbose())
					e.printStackTrace();
			}
		}

	}

}