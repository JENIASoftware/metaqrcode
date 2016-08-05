package it.jenia.metaqrcode.server.authentication.util;

import org.junit.Test;

public class SplitTest {
	@Test
	public void test() {
		String in = "Token fjahfkashdsakhdsa";
		String[] tkn = in.split("[ \\t]");
		for (String s : tkn) {
			System.out.println(s);
		}
	}
}