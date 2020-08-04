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

	public static void main(String[] args) {
		String inFileName = "test.spl";
		String errorsFileName = "errors.txt";
		String outCodeFileName;
		
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
			System.out.println("Usage: \"java -jar .\\exportedJar.jar inputfile outputfile\"");
		}
		
		outCodeFileName = inFileName.replaceFirst("[.][^.]+$", "") + ".out";
		
		// create console logger
		Logger clogger = LoggerFactory.getLogger();
				
		try{   
			FileInputStream is = new FileInputStream(inFileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
        

			clogger.writeLine("Input File: " + inFileName);
			clogger.writeLine("Output File: " + errorsFileName);

			clogger.writeLine();
			
			//create lexer
			SimplePlusLexer lexer = new SimplePlusLexer(input);
			// disable default ANTLR lexer listener (to override default behavior)
			lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
			lexer.addErrorListener(new SimpleLexerErrorListener(LoggerFactory.getLogger(errorsFileName)));
			lexer.addErrorListener(new SimpleLexerErrorListener(clogger));

			
			clogger.writeLine("Collecting Tokens...");

			//create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			
			clogger.writeLine("...Tokens collected");

			clogger.writeLine();
			
			SimplePlusParser parser = new SimplePlusParser(tokens);
			SimpleErrorListener sl = new SimpleSintaxErrorListener(LoggerFactory.getLogger(errorsFileName));
			
			// disable default ANTLR syntax listener (to override default behavior)
			parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
			// logger returned from LoggerFactory writes on errors.txt file
			parser.addErrorListener(sl);
			parser.addErrorListener(new SimpleSintaxErrorListener(clogger));

			//tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			//build custom visitor
			VisitorImplSP visitor = new VisitorImplSP();
			
			//visit the root, this will recursively visit the whole tree
			StmtBlock mainBlock = (StmtBlock) visitor.visitBlock(parser.block());
			
			if(lexer.errors.size() > 0) {
				for (LexicalError error : lexer.errors)
					clogger.writeLine("Lexical Error: " + error.toString());
				quit(clogger);
			}
			
			if (sl.errorsDetected()) {
				quit(clogger);
			}
	
			clogger.writeLine("Checking semantic...");
			
			List<SemanticError> errors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());
			
			if (errors.size() > 0) {
				clogger.writeLine("Check semantics FAILED");
				for (SemanticError err : errors)
					clogger.writeLine(err.toString());
				quit(clogger);
			} 
			
			clogger.writeLine("Check semantic succeeded");

			clogger.writeLine();
			
			clogger.writeLine("Checking types...");
				
			mainBlock.inferType();
			
			if (TypeErrorsStorage.getTypeErrors().size() > 0) {
				clogger.writeLine("Type check FAILED");
				for (TypeError err : TypeErrorsStorage.getTypeErrors())
					clogger.writeLine(err.toString());
				quit(clogger);
			} 

			clogger.writeLine("Type check succeeded");

			clogger.writeLine();
			
			List<BehaviourError> bErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());
			
			if (bErrors.size() > 0) {
				clogger.writeLine("Behavioural analysis FAILED");			
				for(SemanticError bErr: bErrors)
					clogger.writeLine(bErr.toString());
				quit(clogger);
			}
			clogger.writeLine("Behavioural analysis succeeded");
			String code = mainBlock.codeGen();
			LoggerFactory.getLogger(outCodeFileName).write(code.replaceFirst("\r\n", ""));
			
			is = new FileInputStream(outCodeFileName);
			input = new ANTLRInputStream(is);
       
			clogger.writeLine("Input File: " + outCodeFileName);

			clogger.writeLine();
			
			//create lexer
			SVMLexer SVMlexer = new SVMLexer(input);

			//create parser
			tokens = new CommonTokenStream(SVMlexer);
			
			
			SVMParser SVMparser = new SVMParser(tokens);
			
			//tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			//build custom visitor
			VisitorImplSVM SVMVisitor = new VisitorImplSVM();
			
			//visit the root, this will recursively visit the whole tree
			SVMVisitor.visitAssembly(SVMparser.assembly());
			
			new ExecuteVM(SVMVisitor.getCode()).cpu();
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void quit(Logger clogger) {
		try {
			clogger.writeLine("Quitting compiling");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(-1);
	}
}
