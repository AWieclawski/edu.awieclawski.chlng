package min.window.substring;

import java.util.Arrays;

/**
 * CODERBYTE
 * 
 * Min Window Substring
 * 
 * Have the function MinWindowSubstring(strArr) take the array of strings stored
 * in strArr, which will contain only two strings, the first parameter being the
 * string N and the second parameter being a string K of some characters, and
 * your goal is to determine the smallest substring of N that contains all the
 * characters in K. For example: if strArr is ["aaabaaddae", "aed"] then the
 * smallest substring of N that contains the characters a, e, and d is "dae"
 * located at the end of the string. So for this example your program should
 * return the string dae.
 * 
 * Another example: if strArr is ["aabdccdbcacd", "aad"] then the smallest
 * substring of N that contains all of the characters in K is "aabd" which is
 * located at the beginning of the string. Both parameters will be strings
 * ranging in length from 1 to 50 characters and all of K's characters will
 * exist somewhere in the string N. Both strings will only contains lowercase
 * alphabetic characters.
 * 
 * Examples
 * 
 * Input: new String[] {"ahffaksfajeeubsne", "jefaa"} Output: aksfaje
 * 
 * Input: new String[] {"aaffhkksemckelloe", "fhea"} Output: affhkkse
 * 
 */
public class Solution {
	private final static int ALPH_QTY = 26; // number of letters in the alphabet
	private static int[] TARGET;
	private static int[][] TARG_IN_SRC;
	private static String MINIMIZED;
	private static String BASE;
	private static int MAX_INDX;
	private static int MIN_INDX;
	private static int[] MIN_ORDNTS; // targetInSource co-ordinates
	private static int[] MAX_ORDNTS; // targetInSource co-ordinates

	private static String getWindow(int[][] intTmpArr) {
		int count = 0;
		int notZeroCount = 0;
		System.out.println(" -- ");
		for (int[] arrInt : intTmpArr) {
			notZeroCount = countNotZeroInArray(arrInt);
			if (TARGET[count] > 0 && notZeroCount >= TARGET[count]) {
				getMinFromArray(arrInt, count, notZeroCount);
				getMaxFromArray(arrInt, count, notZeroCount);
				System.out.println(count + "|" + Arrays.toString(arrInt) + ",MIN_INDX=" + MIN_INDX + ",MAX_INDX="
						+ MAX_INDX + ",MIN_ORDNTS=" + Arrays.toString(MIN_ORDNTS) + ",MAX_ORDNTS="
						+ Arrays.toString(MAX_ORDNTS));
			}
			count++;
		}
		return BASE.substring(MIN_INDX - 1, MAX_INDX);
	}

	private static int countNotZeroInArray(int[] arr) {
		int count = 0;
		for (int i : arr) {
			if (i > 0)
				count++;
		}
		return count;
	}

	private static void getMinFromArray(int[] minArr, int alphNo, int remains) {
		int count = 0;
		for (int min : minArr) {
			if (min < MIN_INDX && min > 0) {
				MIN_INDX = min;
				MIN_ORDNTS = new int[] { alphNo, count, remains };
			}
			count++;
		}
	}

	private static void getMaxFromArray(int[] maxArr, int alphNo, int remains) {
		int count = 0;
		for (int max : maxArr) {
			if (max > MAX_INDX && max > 0) {
				MAX_INDX = max;
				MAX_ORDNTS = new int[] { alphNo, count, remains };
			}
			count++;
		}
	}

	private static boolean compareTarget(int[] source) {
		for (int i : TARGET) {
			if (TARGET[i] > source[i])
				return false;
		}
		return true;
	}

	private static int[] getCharCounter(String source) {
		int[] result = new int[ALPH_QTY];
		for (char ch : source.toCharArray()) {
			result[ch - 'a']++;
		}
		return result;
	}

	private static int[][] getTargetInSource(String source) {
		int[][] targetInSource = new int[ALPH_QTY][source.length()];
		int[] indexCounter = new int[ALPH_QTY];
		int index = 1; // starts from 1
		for (char ch : source.toCharArray()) {
			int alphabetNo = (ch - 'a');
			if (TARGET[alphabetNo] > 0) {
				targetInSource[alphabetNo][indexCounter[alphabetNo]] = index;
				indexCounter[alphabetNo]++;
			}
			index++;
		}
		return targetInSource;
	}

	private static boolean fitWindow(int[][] intTmpArr) {
		MINIMIZED = getWindow(intTmpArr);
		if (compareTarget(getCharCounter(MINIMIZED))) {
			return true;
		}
		return false;
	}

	public static String MinWindowSubstring(String[] strArr) {
		final String N = strArr[0];
		final String K = strArr[1];
		String result = MINIMIZED = BASE = N;

		TARGET = getCharCounter(K);
		TARG_IN_SRC = getTargetInSource(N);

		MIN_INDX = Integer.MAX_VALUE;
		MAX_INDX = Integer.MIN_VALUE;
		if (!fitWindow(TARG_IN_SRC))
			return K + " no fits to " + N;

		int minimizeIndicator = 0;

		// do minimize
		while (minimizeIndicator < 2) {
			minimizeIndicator = 0;
			if (TARGET[MIN_ORDNTS[0]] < MIN_ORDNTS[2]) {
				int[][] intTmpArr = TARG_IN_SRC;
				intTmpArr[MIN_ORDNTS[0]][MIN_ORDNTS[1]] = 0;
				MIN_INDX = Integer.MAX_VALUE;
				if (fitWindow(intTmpArr)) {
					TARG_IN_SRC = intTmpArr;
					result = MINIMIZED;
				} else
					minimizeIndicator++;
			} else
				minimizeIndicator++;

			if (TARGET[MAX_ORDNTS[0]] < MAX_ORDNTS[2]) {
				int[][] intTmpArr = TARG_IN_SRC;
				intTmpArr[MAX_ORDNTS[0]][MAX_ORDNTS[1]] = 0;
				MAX_INDX = Integer.MIN_VALUE;
				if (fitWindow(intTmpArr)) {
					TARG_IN_SRC = intTmpArr;
					result = MINIMIZED;
				} else
					minimizeIndicator++;
			} else
				minimizeIndicator++;
		}

		System.out.println(K);
		System.out.println(Arrays.toString(TARGET));
//		System.out.println("min:" + (char) N.codePointAt(MIN_INDX - 1));
//		System.out.println("max:" + (char) N.codePointAt(MAX_INDX - 1));
//		System.out.println(Arrays.deepToString(targetInSource));

		// TODO optimize to shortest string containing pattern K

		return result;
	}

	public static void main(String[] args) {

		String[] strArr = new String[] { "ahffaksfajeeubsne", "jefaa" }; // aksfaje
		System.out.println(MinWindowSubstring(strArr));

		strArr = new String[] { "aaffhkksemckelloe", "fhea" }; // affhkkse
		System.out.println(MinWindowSubstring(strArr));

		strArr = new String[] { "aabdccdbcacd", "aad" }; // aabd
		System.out.println(MinWindowSubstring(strArr));

		strArr = new String[] { "aaabaaddae", "aed" }; // dae
		System.out.println(MinWindowSubstring(strArr));
	}

}
