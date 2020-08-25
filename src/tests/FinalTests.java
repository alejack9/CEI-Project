package tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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

class FinalTests {

	@AfterEach
	public void resetTypeErrorsStorage() {
		TypeErrorsStorage.clear();
	}

	StmtBlock lexerErrors(String code, int number) throws IOException {
		SimplePlusLexer lexer = new SimplePlusLexer(new ANTLRInputStream(new ByteArrayInputStream(code.getBytes())));
		
		SimplePlusParser parser = new SimplePlusParser(new CommonTokenStream(lexer));
		
		parser.setBuildParseTree(true);
		
		VisitorImplSP visitor = new VisitorImplSP();
		
		StmtBlock mainBlock = (StmtBlock) visitor.visitBlock(parser.block());
		
		assertEquals(number, lexer.errors.size());
		
		return mainBlock;
	}
	
	StmtBlock semanticErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = lexerErrors(code, 0);
		
		List<SemanticError> semanticErrors = mainBlock.checkSemantics(new ListOfMapEnv<STEntry>());

		assertEquals(number, semanticErrors.size());
		return mainBlock;
	}
	
	StmtBlock typeErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = semanticErrors(code, 0);
		
		mainBlock.inferType();

		assertEquals(number, TypeErrorsStorage.getTypeErrors().size());
		return mainBlock;

	}

	StmtBlock behaviourErrors(String code, int number) throws IOException {
		StmtBlock mainBlock = typeErrors(code, 0);
		List<BehaviourError> behaviourErrors = mainBlock.inferBehaviour(new ListOfMapEnv<BTEntry>());

		assertEquals(number, behaviourErrors.size());
		
		return mainBlock;
	}

	void runCode(String code) throws IOException {
		SVMParser SVMparser = new SVMParser(
				new CommonTokenStream(
						new SVMLexer(
								new ANTLRInputStream(
										new ByteArrayInputStream(
												code.replaceFirst("\r\n", "").getBytes()
		)))));
		
		SVMparser.setBuildParseTree(true);
		
		VisitorImplSVM SVMVisitor = new VisitorImplSVM();
		
		SVMVisitor.visitAssembly(SVMparser.assembly());
		
		assertDoesNotThrow(() -> new ExecuteVM(SVMVisitor.getCode()).cpu());
	}
	
	void correctCode(String code) throws IOException {
		StmtBlock mainBlock = behaviourErrors(code, 0);
		runCode(mainBlock.codeGen());
	}
	
	@Test
	void correctCode1() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x = 3;"
				, "    int f(int var a, bool b) {"
				, "        if(b == true) {"
				, "            return a;"
				, "        }"
				, "        "
				, "        int g(int c) {"
				, "            int h(int d) {"
				, "                return d/2;"
				, "            }"
				, "            return h(10)/c;"
				, "        }"
				, ""
				, "        print b;"
				, "        return g(5);"
				, "    }"
				, ""
				, "    f(x, false);"
				, "    print x;"
				, "}")
		);
	}
	
	@Test
	void correctCode2() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x = -1511 + 201;"
				, "    int fun(int y) {"
				, "        return y;"
				, "    }"
				, ""
				, "    x = fun(4);"
				, "    print x;"
				, "}")
		);
	}

	@Test
	void correctCode3() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x;"
				, "    if(x == 3) {"
				, "        delete x;"
				, "    } else int x;"
				, "}")
		);
	}

	@Test
	void correctCode4() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x;"
				, "    if(x == 3) {"
				, "        delete x;"
				, "    } else int x;"
				, ""
				, "    int x = 3;"
				, "}")
		);
	}

	@Test
	void correctCode5() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x;"
				, "    if(x == 3) {"
				, "        delete x;"
				, "    } else "
				, "        x = 5;"
				, ""
				, "    int x = 3;"
				, "}")
		);
	}

	@Test
	void correctCode6() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int x;"
				, "    "
				, "    void f(int var y) {"
				, "        void g(int var z) {"
				, "            void h(int var q) {"
				, "                delete q;"
				, "                int q;"
				, "            }"
				, "            delete z;"
				, "            int z;"
				, "            h(z);"
				, "        }"
				, "        delete y;"
				, "        int y;"
				, "        g(y);"
				, "    }"
				, "    f(x);"
				, "}")
		);
	}

	@Test
	void correctCode7() throws IOException {
		correctCode(String.join("\r\n" 
				, "{"
				, "    int a = 4;"
				, "    int b;"
				, "    void g(int var x, int var y) {"
				, "        if (x == 0) { return; }"
				, "        else {"
				, "            bool z;"
				, "            x = x - 1;"
				, "            g(x,y);"
				, "        }"
				, "    }"
				, "    g(a,b);"
				, "    print a;"
				, "}")
		);
	}
	
	@Test
	void correctCode8() throws IOException {
		correctCode(String.join("\r\n" 
				,"{"
				,"	int fun() {"
				,"		int x = 7;"
				,"		if (x == 8) { }"
				,"		return x;"
				,"	}"
				,"	bool fun1(int y) {"
				,"		if (y > 4) {"
				,"			return true;"
				,"		} else {"
				,"			return false;"
				,"		}"
				,"	}"
				,"}"
				)
		);
	}
	
	
	@Test
	void cannotChangeTypeAfterDelete() throws IOException {
		semanticErrors(String.join("\r\n" 
				, "{"
				, "    int x; delete x; bool x;"
				, "}")
		, 1);
	}

	@Test
	void variableAHasBeenDeleted() throws IOException {
		behaviourErrors(String.join("\r\n" 
				, "{"
				, "    void g(int var x, int var y) {"
				, "        int a = 2;"
				, "" 
				, "        if(x == 1) {"
				, "            delete a;"
				, "            delete x;"
				, "            y = 3;"
				, "        }"
				, "        else {"
				, "            delete y;"
				, "            a = 2;"
				, "        }"
				, "" 
				, "        print a;"
				, "    }"
				, "" 
				, "    int x;"
				, "    int y;"
				, "    g(x, y);"
				, "}")
		, 1);
	}

	@Test
	void variableXHasBeenDeleted() throws IOException {
		behaviourErrors(String.join("\r\n" 
				, "{"
				, "    int a;"
				, "    int b;"
				, "    a = 4;"
				, "    void g( int var x, int var  y)"
				, "        { "
				, "            if (y==0)"
				, "                delete x ;"
				, "            else {"
				, "                bool z;"
				, "                x = x-1;"
				, "                g(x,y) ;"
				, "                delete x;"
				, "            }"
				, "        }"
				, "    g(a,b);"
				, "}")
		, 3);
	}

	@Test
	void variableZHasBeenDeleted() throws IOException {
		behaviourErrors(String.join("\r\n" 
				, "{"
				, "    int z = 3;"
				, "    int x = 3;"
				, "    void fun(int var x, int var y) {"
				, "        delete x;"
				, "        y = 3;"
				, "    }"
				, ""
				, "    fun(z,x);"
				, "    z = 3;"
				, "}")
		, 1);
	}

	@Test
	void cannotAccessToGlobalVariables() throws IOException {
		semanticErrors(String.join("\r\n" 
				, "{"
				, "    int x;"
				, "    int f(int y) {"
				, "        return x + y;"
				, "    }"
				, "    print f(3);"
				, "}")
		, 1);
	}

	@Test
	void typeChecking() throws IOException {
		typeErrors(String.join("\r\n" 
				, "{"
				, "    int x = 4;"
				, "    x = true;"
				, ""
				, "    bool y = true;"
				, "    y = 4;"
				, "}")
		, 2);
	}
	
	@Test
	void refValueCheck() throws IOException {
		semanticErrors(String.join("\r\n" 
				, "{"
				, "    void f(int var x, int y) {"
				, "        print x;"
				, "    }"
				, "	"
				, "	f(1+1, 0);"
				, "}")
		, 1);
	}
	
	@Test
	void lexerErrors() throws IOException {
		lexerErrors(String.join("\r\n"
				, "{"
				, "    bool valu';" 
				, "}")
		, 1);
	}	
}
