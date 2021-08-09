package min.window.substring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
	private final static int ALPH_QTY = 26; // number of letters in the alphabet const.
	private static int[] K_MATRIX_BASE; // count array of all K letters const.
	private static int[][] K_MATRIX_N_BASE; // count array of all K letters in N String const.
	private static String N_BASE; // N String constans
	private static Set<String> COMBINATIONS_BASE = new HashSet<>();
	private static int[][] K_matrix_N_oper; // count array of all K letters in N String variable
	private static String minimized;
	private static int maxIndex;
	private static int minIndex;
	private static int[] minOrdnts; // targetInSource co-ordinates
	private static int[] maxOrdnts; // targetInSource co-ordinates

	private static String getWindow(int[][] intTmpArr) {
		int count = 0;
		int notZeroCount = 0;
		System.out.println(" -- ");
		for (int[] arrInt : intTmpArr) {
			notZeroCount = countNotZeroInArray(arrInt);
			if (K_MATRIX_BASE[count] > 0 && notZeroCount >= K_MATRIX_BASE[count]) {
				getMinFromArray(arrInt, count, notZeroCount);
				getMaxFromArray(arrInt, count, notZeroCount);
				System.out.println(count + "|" + Arrays.toString(arrInt) + ",MIN_INDX=" + minIndex + ",MAX_INDX="
						+ maxIndex + ",MIN_ORDNTS=" + Arrays.toString(minOrdnts) + ",MAX_ORDNTS="
						+ Arrays.toString(maxOrdnts));
			}
			count++;
		}
		return N_BASE.substring(minIndex - 1, maxIndex);
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
			if (min < minIndex && min > 0) {
				minIndex = min;
				minOrdnts = new int[] { alphNo, count, remains };
			}
			count++;
		}
	}

	private static void getMaxFromArray(int[] maxArr, int alphNo, int remains) {
		int count = 0;
		for (int max : maxArr) {
			if (max > maxIndex && max > 0) {
				maxIndex = max;
				maxOrdnts = new int[] { alphNo, count, remains };
			}
			count++;
		}
	}

	private static boolean compareTarget(int[] source) {
		for (int i : K_MATRIX_BASE) {
			if (K_MATRIX_BASE[i] > source[i])
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
			if (K_MATRIX_BASE[alphabetNo] > 0) {
				targetInSource[alphabetNo][indexCounter[alphabetNo]] = index;
				indexCounter[alphabetNo]++;
			}
			index++;
		}
		return targetInSource;
	}

	private static boolean fitWindow(int[][] intTmpArr) {
		minimized = getWindow(intTmpArr);
		if (compareTarget(getCharCounter(minimized))) {
			return true;
		}
		return false;
	}

	private static void indexReset() {
		minIndex = Integer.MAX_VALUE;
		maxIndex = Integer.MIN_VALUE;
	}

	private static boolean doMinimizeDown() {
		boolean indicator = false;
		if (K_MATRIX_BASE[minOrdnts[0]] < minOrdnts[2]) {
			int[][] intTmpArr = K_matrix_N_oper;
			intTmpArr[minOrdnts[0]][minOrdnts[1]] = 0;
			indexReset();
			if (fitWindow(intTmpArr)) {
				K_matrix_N_oper = intTmpArr;
				indicator = true;
			}
		}
		return indicator;
	}

	private static boolean doMinimizeUp() {
		boolean indicator = false;
		if (K_MATRIX_BASE[maxOrdnts[0]] < maxOrdnts[2]) {
			int[][] intTmpArr = K_matrix_N_oper;
			intTmpArr[maxOrdnts[0]][maxOrdnts[1]] = 0;
			indexReset();
			if (fitWindow(intTmpArr)) {
				K_matrix_N_oper = intTmpArr;
				indicator = true;
			}
		}
		return indicator;
	}

	private static void buildCombination(int elementsNo, int depth, StringBuffer output) {
		if (depth == 0) {
//            System.out.println(output);
			COMBINATIONS_BASE.add(output.toString());
		} else {
			for (int i = 0; i < elementsNo; i++) {
				output.append(i);
				buildCombination(elementsNo, depth - 1, output);
				output.deleteCharAt(output.length() - 1);
			}
		}
	}

	private static int getCombinationsDepth(int[] a, int[] b) {
		int suma = 0;
		for (int i : a)
			suma += i;
		int sumb = 0;
		for (int i : b)
			sumb += i;
		return Math.abs(suma - sumb);
	}

	public static String MinWindowSubstring(String[] strArr) {
		final String N = strArr[0];
		final String K = strArr[1];
		String result = minimized = N_BASE = N;

		K_MATRIX_BASE = getCharCounter(K);
		K_matrix_N_oper = K_MATRIX_N_BASE = getTargetInSource(N);

		indexReset();
		if (!fitWindow(K_matrix_N_oper))
			return K + " no fits to " + N;

		int minimizeIndicator = 0;

		// very primitive optimization algorithm
		// getting substring of N containing pattern K
		while (minimizeIndicator < 2) {
			minimizeIndicator = 0;

			if (!doMinimizeDown())
				minimizeIndicator++;

			if (!doMinimizeUp())
				minimizeIndicator++;
		}

		// TODO implement many algorithms to get shortest result among them

		result = minimized;

		System.out.println("N:" + N + ",K:" + K);
		System.out.println(Arrays.toString(K_MATRIX_BASE));
//		System.out.println("min:" + (char) N.codePointAt(MIN_INDX - 1));
//		System.out.println("max:" + (char) N.codePointAt(MAX_INDX - 1));
//		System.out.println(Arrays.deepToString(targetInSource));

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
