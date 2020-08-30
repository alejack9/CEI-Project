package tests;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import util_analysis.TypeErrorsStorage;

class WrongCodes {

	/**
	 * Reset type errors storage after each test.
	 */
	@AfterEach
	public void resetTypeErrorsStorage() {
		TypeErrorsStorage.clear();
	}

	/**
	 * Cannot change variable type after a delete.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void cannotChangeTypeAfterDelete() throws IOException {
		Utils.semanticErrors(String.join("\r\n" 
				, "{"
				, "    int x; delete x; bool x;"
				, "}")
		, 1);
	}

	/**
	 * The variable "a" has been deleted.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * The variable "x" has been deleted (a different example).
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * The variable "z" has been deleted (a different example).
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Parameters must be compliant.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void paramsMustBeCompliant() throws IOException {
		Utils.semanticErrors(String.join("\r\n"
				, "{"
				, "    void f(int x) { }"
				, "    f();"
				, "}")
		, 1);
	}

	/**
	 * Check if a variable is passed in case of a reference is required.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Cannot access global variables inside a function.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Type checking example.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Lexical error.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void lexerErrors() throws IOException {
		Utils.lexicalErrors(String.join("\r\n"
				, "{"
				, "    bool valu';" 
				, "}")
		, 1);
	}

	/**
	 * Aliasing error with a variable that has previously changed his status
	 * (previously deleted)
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void aliasingError() throws IOException {
		Utils.behaviourErrors(String.join("\r\n"
				, "{"
				, "    int x;"
				, "    delete x;"
				, "    int x;"
				, "    "
				, "    void f(int var y, int var z) {"
				, "        delete y;"
				, "        print z;"
				, "    }"
				, "    "
				, "    f(x, x);"
				, "}")
		, 1);
	}
	
	@Test
	void parseError() throws IOException {
		Utils.parseErrors(String.join("\r\n"
				, "{"
				, "    int x; [A]"
				, "}")
		, true);
	}
}