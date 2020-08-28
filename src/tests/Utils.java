package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ast.StmtBlock;
import ast.VisitorImplSP;
import ast.VisitorImplSVM;
import ast.errors.BehaviourError;
import ast.errors.SemanticError;
import parser.ExecuteVM;
import parser.SVMLexer;
import parser.SVMParser;
import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import util_analysis.ListOfMapEnv;
import util_analysis.TypeErrorsStorage;
import util_analysis.entries.BTEntry;
import util_analysis.entries.STEntry;

class Utils {
	private Utils() {
	}

	public static StmtBlock lexerErrors(String code, int number) throws IOException {
		SimplePlusLexer lexer = new SimplePlusLexer(new ANTLRInputStream(new ByteArrayInputStream(code.getBytes())));

		SimplePlusParser parser = new SimplePlusParser(new CommonTokenStream(lexer));

		parser.setBuildParseTree(true);

		VisitorImplSP visitor = new VisitorImplSP();

		StmtBlock mainBlock = (StmtBlock) visitor.visitBlock(parser.block());

		assertEquals(number, lexer.errors.size());

		return mainBlock;
	}

	public static StmtBlock semanticErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = lexerErrors(code, 0);

		List<SemanticError> semanticErrors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());

		assertEquals(number, semanticErrors.size());
		return mainBlock;
	}

	public static StmtBlock typeErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = semanticErrors(code, 0);

		mainBlock.inferType();

		assertEquals(number, TypeErrorsStorage.getTypeErrors().size());
		return mainBlock;

	}

	public static StmtBlock behaviourErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = typeErrors(code, 0);
		List<BehaviourError> behaviourErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());

		assertEquals(number, behaviourErrors.size());

		return mainBlock;
	}

	public static void runCode(String code) throws IOException {
		SVMParser SVMparser = new SVMParser(new CommonTokenStream(new SVMLexer(
				new ANTLRInputStream(new ByteArrayInputStream(code.replaceFirst("\r\n", "").getBytes())))));

		SVMparser.setBuildParseTree(true);

		VisitorImplSVM SVMVisitor = new VisitorImplSVM();

		SVMVisitor.visitAssembly(SVMparser.assembly());

		assertDoesNotThrow(() -> new ExecuteVM(SVMVisitor.getCode()).cpu());
	}

	public static void correctCode(String code) throws IOException {
		StmtBlock mainBlock = behaviourErrors(code, 0);
		runCode(mainBlock.codeGen());
	}

}
