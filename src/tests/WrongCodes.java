package tests;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import util_analysis.TypeErrorsStorage;

class WrongCodes {

	@AfterEach
	public void resetTypeErrorsStorage() {
		TypeErrorsStorage.clear();
	}

	
	
	@Test
	void cannotChangeTypeAfterDelete() throws IOException {
		Utils.semanticErrors(String.join("\r\n" 
				, "{"
				, "    int x; delete x; bool x;"
				, "}")
		, 1);
	}

	@Test
	void variableAHasBeenDeleted() throws IOException {
		Utils.behaviourErrors(String.join("\r\n" 
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
		Utils.behaviourErrors(String.join("\r\n" 
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
		Utils.behaviourErrors(String.join("\r\n" 
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
	void paramsMustBeCompliant() throws IOException {
		Utils.semanticErrors(String.join("\r\n"
				, "{"
				, "    void f(int x) { }"
				, "    f();"
				, "}")
		, 1);
	}
	
	@Test
	void refValueCheck() throws IOException {
		Utils.semanticErrors(String.join("\r\n" 
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
	void cannotAccessToGlobalVariables() throws IOException {
		Utils.semanticErrors(String.join("\r\n" 
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
		Utils.typeErrors(String.join("\r\n" 
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
	void lexerErrors() throws IOException {
		Utils.lexerErrors(String.join("\r\n"
				, "{"
				, "    bool valu';" 
				, "}")
		, 1);
	}
	
	
}
