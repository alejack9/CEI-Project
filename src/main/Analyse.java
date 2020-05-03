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

		String fileName = "test.spl";

		// create console logger
		Logger clogger = LoggerFactory.getLogger(false);

		try {
			FileInputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);

			SimpleLexer lexer = new SimpleLexer(input);

			// disable default ANTLR lexer listener (to override default behavior)
			lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
			lexer.addErrorListener(new SimpleLexerErrorListener(LoggerFactory.getLogger("errors.txt", false)));
			lexer.addErrorListener(new SimpleLexerErrorListener(clogger));

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimpleParser parser = new SimpleParser(tokens);

			// disable default ANTLR syntax listener (to override default behavior)
			parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
			// logger returned from LoggerFactory writes on errors.txt file
			parser.addErrorListener(new SimpleSintaxErrorListener(LoggerFactory.getLogger("errors.txt", false)));
			parser.addErrorListener(new SimpleSintaxErrorListener(clogger));

			parser.setBuildParseTree(true);

			VisitorImpl visitor = new VisitorImpl();

			ElementBase mainBlock = visitor.visitBlock(parser.block());

			// fill errors list with semantics errors
			List<SemanticError> errors = mainBlock.checkSemantics(new Environment());

			/*
			 * EXERCISE 3: check if duplicated ID error exists in semantic errors list
			 * (IDALREADYEXISTS to check function's ID duplicates,
			 * VARIABLEALREADYEXISTS to check variable's ID duplicates)
			 */
			clogger.writeLine("There are same ID in the same block: "
					+ errors.stream().anyMatch(s -> s.getType() == SemanticErrorType.IDALREADYEXISTS
							|| s.getType() == SemanticErrorType.VARIABLEALREADYEXISTS));

			if (errors.size() > 0) {
				clogger.writeLine("Check semantics FAILED");
				for (SemanticError err : errors)
					clogger.writeLine(err.toString());
			} else {
				clogger.writeLine();
				clogger.writeLine("Check semantics succeded");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			try {
				clogger.writeLine("Error while semantic checking");
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if(clogger.isVerbose())
					e.printStackTrace();
			}
		}

	}

}
