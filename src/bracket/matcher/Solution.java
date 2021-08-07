package bracket.matcher;

import java.util.Stack;

/**
 * CODERBYTE
 * 
 * Bracket Matcher
 * 
 * Have the function BracketMatcher(str) take the str parameter being passed and
 * return 1 if the brackets are correctly matched and each one is accounted for.
 * Otherwise return 0. For example: if str is "(hello (world))", then the output
 * should be 1, but if str is "((hello (world))" the the output should be 0
 * because the brackets do not correctly match up. Only "(" and ")" will be used
 * as brackets. If str contains no brackets return 1.
 * 
 * Examples
 * 
 * Input: "(coder)(byte))" Output: 0
 * 
 * Input: "(c(oder)) b(yte)" Output: 1
 *
 */
public class Solution {

	public static String BracketMatcher(String str) {
		Stack<Character> stack = new Stack<Character>();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c == '(')
				stack.push(c);
			else if (c == ')')
				if (stack.empty())
					return "0";
				else if (stack.peek() == '(')
					stack.pop();
				else
					return "0";
		}
		return (stack.empty() ? "1" : "0");
	}

	public static void main(String[] args) {
		// keep this function call here
		String str = "";

		str = "(coder)(byte))"; // 0
		System.out.println(BracketMatcher(str));

		str = "(c(oder)) b(yte)"; // 1
		System.out.println(BracketMatcher(str));

		str = "coder(b)(y)(t)(e))"; // 0
		System.out.println(BracketMatcher(str));

		str = "letter(s) gal(o)(r)((e)"; // 0
		System.out.println(BracketMatcher(str));
	}

}
