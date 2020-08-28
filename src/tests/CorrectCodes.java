/*
 * 
 */
package tests;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import util_analysis.TypeErrorsStorage;

class CorrectCodes {

	/**
	 * Reset type errors storage.
	 */
	@AfterEach
	public void resetTypeErrorsStorage() {
		TypeErrorsStorage.clear();
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
}
