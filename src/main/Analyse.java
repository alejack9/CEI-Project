package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;

import ast.SimpleElementBase;
import ast.SimpleStmtBlock;
import ast.SimpleVisitorImpl;
import ast.exceptions.SemanticError;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import parser.SimpleLexer;
import parser.SimpleParser;
import util_analysis.Environment;

public class Analyse {

	public static void main(String[] args) {
		
		String fileName = "test.spl";
		
		try{   
			FileInputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
        
			//create lexer
			SimpleLexer lexer = new SimpleLexer(input);
			lexer.addErrorListener(new ANTLRErrorListener() {
				
				@Override
				public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
						String msg, RecognitionException e) {
					System.out.println(recognizer.getClass().getName());
				}
				
				@Override
				public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
						ATNConfigSet configs) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
						BitSet conflictingAlts, ATNConfigSet configs) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
						BitSet ambigAlts, ATNConfigSet configs) {
					// TODO Auto-generated method stub
					
				}
			});
			
			//create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimpleParser parser = new SimpleParser(tokens);
			
			parser.addErrorListener(new ANTLRErrorListener() {
				@Override
				public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
					//Scrittura sul file "lexErr.txt"
//					try {
//						errOutput.write(String.format("line %d:%d %s\n", i, i1, s));
						System.out.println(recognizer.getClass().getName());
//					} catch (IOException ioEx) {
//						System.err.println("IOException " + ioEx);
//					}
				}

				@Override
				public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {

				}

				@Override
				public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {

				}

				@Override
				public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {

				}
			});
			
			//tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			//build custom visitor
			SimpleVisitorImpl visitor = new SimpleVisitorImpl();
			
			//visit the root, this will recursively visit the whole tree
			SimpleElementBase mainBlock = visitor.visitBlock(parser.block());
			
			
			//check semantics
			//give a fresh environment, no need to make it persist
			List<SemanticError> errors = mainBlock.checkSemantics(new Environment());
			
			//this means the semantic checker found some errors
			if (errors.size() > 0) {
				System.out.println("Check semantics FAILED");			
				for(SemanticError err: errors)
					System.out.println(err);
			}else{
				System.out.println("Check semantics succeded");
//				System.out.println("Calculating behavioral type");
				
				//give a fresh environment, no need to make it persist
//				BTBlock res = (BTBlock)mainBlock.inferBehavior(new Environment());
//				
//				System.out.println(res.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
