package string.reduction;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CODEBYTE
 * 
 * String Reduction
 * 
 * Have the function StringReduction(str) take the str parameter being passed
 * and return the smallest number you can get through the following reduction
 * method. The method is: Only the letters a, b, and c will be given in str and
 * you must take two different adjacent characters and replace it with the
 * third. For example "ac" can be replaced with "b" but "aa" cannot be replaced
 * with anything. This method is done repeatedly until the string cannot be
 * further reduced, and the length of the resulting string is to be outputted.
 * For example: if str is "cab", "ca" can be reduced to "b" and you get "bb"
 * (you can also reduce it to "cc"). The reduction is done so the output should
 * be 2. If str is "bcab", "bc" reduces to "a", so you have "aab", then "ab"
 * reduces to "c", and the final string "ac" is reduced to "b" so the output
 */
public class Solution {

	// Returns smallest possible length with given
	// operation allowed. Returns String in the task :/
	static String stringReduction(String str) {

		final Logger LOGGER = Logger.getLogger(Solution.class.getName()); // java.util.logging.Logger

		int n = str.length();

		// Counting occurrences of three different
		// characters 'a', 'b' and 'c' in str
		int count[] = new int[3];
		try {
			for (int i = 0; i < n; ++i) {
				// trick works if lower case alphabet character is counted
				count[str.charAt(i) - 'a']++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.log(Level.INFO, e.getMessage());

		}

		// If all characters are the same.
		if (count[0] == n || count[1] == n || count[2] == n) {
			return String.valueOf(n);
		}

		// If all characters are present even number
		// of times or all are present odd number of
		// times.
		if ((count[0] % 2) == (count[1] % 2) && (count[1] % 2) == (count[2] % 2)) {
			return String.valueOf(2);
		}

		// Answer is 1 for all other cases.
		return String.valueOf(1);
	}

	// Driver code
	public static void main(String[] args) {

		String str = "abcbbaacb"; // 1
		System.out.println(stringReduction(str) + "|" + str);
//		str = "3"; //
//		System.out.println(stringReduction(str));
		str = "cab"; // 2
		System.out.println(stringReduction(str) + "|" + str);
		str = "bcab"; // 1
		System.out.println(stringReduction(str) + "|" + str);
		str = "ccccc"; // 5
		System.out.println(stringReduction(str) + "|" + str);
	}
}
//This code is contributed by PrinciRaj1992
