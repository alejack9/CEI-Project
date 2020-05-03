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
			
			// create lexer
			SimpleLexer lexer = new SimpleLexer(input);
			
			lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
			lexer.addErrorListener(new SimpleLexerErrorListener(LoggerFactory.getLogger("errors.txt")));
			lexer.addErrorListener(new SimpleLexerErrorListener(clogger));
			
			// create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimpleParser parser = new SimpleParser(tokens);
		
			parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
			parser.addErrorListener(new SimpleSintaxErrorListener(LoggerFactory.getLogger("errors.txt")));
			parser.addErrorListener(new SimpleSintaxErrorListener(clogger));

			// tell the parser to build the AST
			parser.setBuildParseTree(true);

			// build custom visitor
			VisitorImpl visitor = new VisitorImpl();

			// visit the root, this will recursively visit the whole tree
			ElementBase mainBlock = visitor.visitBlock(parser.block());

			// check semantics
			// give a fresh environment, no need to make it persist
			List<SemanticError> errors = mainBlock.checkSemantics(new Environment());
			
			clogger.writeLine("There are same ID in the same block: " + errors.stream().anyMatch(s -> s instanceof IdAlreadytExistsError || s instanceof VariableAlreadyExistsError ));
		
			// this means the semantic checker found some errors
			if (errors.size() > 0) {
				clogger.writeLine("Check semantics FAILED");
				for (SemanticError err : errors)
					clogger.writeLine(err.toString());
			} else {
				System.out.println();
				clogger.writeLine("Check semantics succeded");
//				clogger.writeLine("Calculating behavioral type");

				// give a fresh environment, no need to make it persist
//				BTBlock res = (BTBlock)mainBlock.inferBehavior(new Environment());
//				
//				clogger.writeLine(res.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
