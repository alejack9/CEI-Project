package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import ast.SPStmtBlock;
import ast.SPVisitorImpl;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

public class Analyse {

	public static void main(String[] args) {
		String fileName = "test.spl";
		
		try{   
			FileInputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
        
			//create lexer
			SimplePlusLexer lexer = new SimplePlusLexer(input);
			
			//create parser
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SimplePlusParser parser = new SimplePlusParser(tokens);
			
			//tell the parser to build the AST
			parser.setBuildParseTree(true);
			
			//build custom visitor
			SPVisitorImpl visitor = new SPVisitorImpl();
			
			//visit the root, this will recursively visit the whole tree
			SPStmtBlock mainBlock = (SPStmtBlock) visitor.visitBlock(parser.block());
			
			List<SemanticError> errors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());
			
			if (errors.size() > 0) {
				System.out.println("Check semantics FAILED");			
				for(SemanticError err: errors)
					System.out.println(err);
			} else {
				System.out.println("Check semantics succeded");
				
				mainBlock.inferType();
				if (TypeErrorsStorage.getTypeErrors().size() > 0) {
					TypeErrorsStorage.getTypeErrors().forEach(System.out::println);
				} else {
					List<BehaviourError> bErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());
					
					if (bErrors.size() > 0) {
						System.out.println("Behavioural Analysis FAILED");			
						for(SemanticError bErr: bErrors)
							System.out.println(bErr);
					}
				}
				
			}
			
			/*
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
				System.out.println("Calculating behavioral type");
				
				//give a fresh environment, no need to make it persist
				BTBlock res = (BTBlock)mainBlock.inferBehavior(new Environment());
				
				System.out.println(res.toString());
			}
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
