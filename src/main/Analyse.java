package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;

import ast.ElementBase;
import ast.VisitorImpl;
import ast.exceptions.IdAlreadytExistsError;
import ast.exceptions.SemanticError;
import ast.exceptions.VariableAlreadyExistsError;
import logger.Logger;
import logger.LoggerFactory;
import parser.SimpleLexer;
import parser.SimpleParser;
import util_analysis.Environment;

public class Analyse {

	public static void main(String[] args) {

		String fileName = "test.spl";
		
		try {
			FileInputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
			
			Logger clogger = LoggerFactory.getLogger();
			
			
			SimpleLexer lexer = new SimpleLexer(input);
			
			lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
			lexer.addErrorListener(new SimpleLexerErrorListener(LoggerFactory.getLogger("errors.txt")));
			lexer.addErrorListener(new SimpleLexerErrorListener(clogger));
			
			
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimpleParser parser = new SimpleParser(tokens);
		
			parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
			parser.addErrorListener(new SimpleSintaxErrorListener(LoggerFactory.getLogger("errors.txt")));
			parser.addErrorListener(new SimpleSintaxErrorListener(clogger));

			
			parser.setBuildParseTree(true);

			
			VisitorImpl visitor = new VisitorImpl();

			
			ElementBase mainBlock = visitor.visitBlock(parser.block());

			
			
			List<SemanticError> errors = mainBlock.checkSemantics(new Environment());
			
			clogger.writeLine("There are same ID in the same block: " + errors.stream().anyMatch(s -> s instanceof IdAlreadytExistsError || s instanceof VariableAlreadyExistsError ));
		
			
			if (errors.size() > 0) {
				clogger.writeLine("Check semantics FAILED");
				for (SemanticError err : errors)
					clogger.writeLine(err.toString());
			} else {
				System.out.println();
				clogger.writeLine("Check semantics succeded");


				



			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
