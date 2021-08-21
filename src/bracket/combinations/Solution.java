package bracket.combinations;

/**
 * CODEBYTE
 * 
 * Bracket Combinations
 * 
 * Have the function BracketCombinations(num) read num which will be an integer
 * greater than or equal to zero, and return the number of valid combinations
 * that can be formed with num pairs of parentheses. For example, if the input
 * is 3, then the possible combinations of 3 pairs of parenthesis, namely:
 * ()()(), are ()()(), ()(()), (())(), ((())), and (()()). There are 5 total
 * combinations when the input is 3, so your program should return 5.
 * 
 * Examples
 * 
 * Input: 3 Output: 5
 * 
 * Input: 2 Output: 2
 * 
 * @author AWieclawski basing
 *         https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
 *
 */
public class Solution {

	private static int counter;

	static void bracketCounter(int pos, int n, int open, int close) {
		if (close == n) {
			counter++;
			return;
		} else {
			if (open > close) {
				bracketCounter(pos + 1, n, open, close + 1);
			}
			if (open < n) {
				bracketCounter(pos + 1, n, open + 1, close);
			}
		}
	}

	public static int BracketCombinations(int num) {
		counter = 0;
		if (num > 0)
			bracketCounter(0, num, 0, 0);
		return counter;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(BracketCombinations(2));
		System.out.println(BracketCombinations(3));
		System.out.println(BracketCombinations(4));

	}

}
