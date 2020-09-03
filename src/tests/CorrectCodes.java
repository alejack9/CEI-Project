package tests;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import util_analysis.TypeErrorsStorage;

class CorrectCodes {

	/**
	 * Reset type errors storage after each test.
	 */
	@AfterEach
	public void resetTypeErrorsStorage() {
		TypeErrorsStorage.clear();
		System.out.println("-----------------");
	}

	@Test
	void correctCode1() throws IOException {
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
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
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int fun() {"
				, "        int x = 7;"
				, "        if (x == 8) { }"
				, "        return x;"
				, "    }"
				, "    bool fun1(int y) {"
				, "        if (y > 4) {"
				, "            return true;"
				, "        } else {"
				, "            return false;"
				, "        }"
				, "    }"
				, "    print fun1(1);", "}")
			);
	}

	@Test
	void correctCode9() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int a = 4;"
				, "    int g(int var y) {"
				, "        print y;"
				, "        return y;"
				, "    }"
				, "    return g(a);"
				, "}")
			);
	}

	/**
	 * Testing AND
	 */
	@Test
	void correctCode10() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int a = 4;"
				, "    void g(int var y) {"
				, "        if(y == 4 && y + 1 == 5)"
				, "            print y;"
				, "    }"
				, "    return g(a);"
				, "}")
			);
	}

	/**
	 * Testing OR
	 */
	@Test
	void correctCode11() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int a = 4;"
				, "    void g(int var y) {"
				, "        if(y == 5 || y + 1 == 5)"
				, "            print y;"
				, "        if(y == 4 || y + 1 == 6)"
				, "            print y;"
				, "    }"
				, "    return g(a);"
				, "}")
			);
	}
	
	/**
	 * Testing function call inside a function call
	 */
	@Test
	void correctCode12() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int f(int x) {"
				, "        return x;"
				, "    }"
				, "    print f(f(1));"
				, "}"
				)
			);
	}
	
	/**
	 * Variable deleted more times before being passed to a function
	 */
	@Test
	void correctCode13() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    int x;"
				, "    delete x;"
				, "    int x;"
				, "    "
				, "    void f(int var y) {"
				, "        delete y;"
				, "    }"
				, "    "
				, "    f(x);"
				, "    int x;"
				, "    print x;"
				, "}"
				)
			);
	}
	
	/**
	 * Example of "for" implemented in SimplePlus
	 */
	@Test
	void correctCode14() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    void action(int base, int max, int step) {"
				, "        print base;"
				, "    }"
				, "    void for(int base, int max, int step) {"
				, "        if(base >= max) return;"
				, "        action(base, max, step);"
				, "        for(base + step, max, step);"
				, "    }"
				, "    int i = 0;"
				, "    for(i, 10, 1);"
				, "}"
				)
			);
	}

	/**
	 * Example of a function that modify the referenced passed variable, int and
	 * bool variants.
	 */
	@Test
	void correctCode15() throws IOException {
		Utils.correctCode(String.join("\r\n"
				, "{"
				, "    void boolModify(bool var x, bool value) {"
				, "        x = value;"
				, "    }"
				, "    void workOnBool(bool x) {"
				, "        print x;"
				, "        if(x == true)"
				, "            boolModify(x, false);"
				, "        else"
				, "            boolModify(x, true);"
				, "        print x;"
				, "    }"
				, "    workOnBool(false);"
				, "    "
				, "    void intModify(int var x, int value) {"
				, "        x = value;"
				, "    }"
				, "    void workOnInt(int x) {"
				, "        print x;"
				, "        if(x > 3)"
				, "            intModify(x, 2);"
				, "        else"
				, "            intModify(x, 10);"
				, "        print x;"
				, "    }"
				, "    workOnInt(8);"
				, "}"
				)
			);
	}
}
