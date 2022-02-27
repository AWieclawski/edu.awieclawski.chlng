package min.window.substring;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.ComparisonFailure;
import org.junit.Test;

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
public class MinWindowSubstringByArrays {
	private String[] strArr = null;
	private final static Logger LOGGER = Logger.getLogger(MinWindowSubstringByArrays.class.getName());

	private final static int ALPH_QTY = 26; // number of letters in the alphabet const.
	private static int[] K_MATRIX_BASE; // count array of all K letters const.
	private static int[][] K_MATRIX_N_BASE; // count array of all K letters in N String const.
	private static String N_BASE; // N String constans
	private static int[][] K_matrix_N_oper; // count array of all K letters in N String variable
	private static String minimized;
	private static int maxIndex;
	private static int minIndex;
	private static int minLength;
	private static int[] minOrdnts; // targetInSource co-ordinates
	private static int[] maxOrdnts; // targetInSource co-ordinates

	private static Map<Integer, String> betterResultsMap;// = new HashMap<>();

	private static String getWindow(int[][] intTmpArr) {
		int count = 0;
		int notZeroCount = 0;
		for (int[] arrInt : intTmpArr) {
			notZeroCount = countNotZeroInArray(arrInt);
			if (K_MATRIX_BASE[count] > 0 && notZeroCount >= K_MATRIX_BASE[count]) {
				getMinFromArray(arrInt, count, notZeroCount);
				getMaxFromArray(arrInt, count, notZeroCount);
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
				minOrdnts = new int[] {
						alphNo, count, remains
				};
			}
			count++;
		}
	}

	private static void getMaxFromArray(int[] maxArr, int alphNo, int remains) {
		int count = 0;
		for (int max : maxArr) {
			if (max > maxIndex && max > 0) {
				maxIndex = max;
				maxOrdnts = new int[] {
						alphNo, count, remains
				};
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
			int[][] intTmpArr = multiArrayClone(K_matrix_N_oper);
			intTmpArr[minOrdnts[0]][minOrdnts[1]] = 0;

			indexReset();
			if (fitWindow(intTmpArr)) {
				K_matrix_N_oper = multiArrayClone(intTmpArr);
				indicator = true;
			}
		}
		return indicator;
	}

	private static boolean doMinimizeUp() {
		boolean indicator = false;
		if (K_MATRIX_BASE[maxOrdnts[0]] < maxOrdnts[2]) {
			int[][] intTmpArr = multiArrayClone(K_matrix_N_oper);
			intTmpArr[maxOrdnts[0]][maxOrdnts[1]] = 0;

			indexReset();
			if (fitWindow(intTmpArr)) {
				K_matrix_N_oper = multiArrayClone(intTmpArr);
				indicator = true;
			}
		}
		return indicator;
	}

	private static void buildUpDownCombination(int depth, StringBuffer output) {
		if (depth == 0) {
//			a reference to the method inside this loop prevents java.lang.OutOfMemoryError
			studyVariation(output.toString());
		}
		else { // 0 or 1 -> UP or DOWN
			for (int i = 0; i < 2; i++) {
				output.append(i);
				buildUpDownCombination(depth - 1, output);
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

	private static void studyVariation(String str) {
		int[] rejectIndicators = new int[2];
		K_matrix_N_oper = multiArrayClone(K_MATRIX_N_BASE);
		minimized = N_BASE;

		indexReset();
		getWindow(K_matrix_N_oper);

		for (char ch : str.toCharArray()) {

			if (rejectIndicators[0] < 1 && rejectIndicators[1] < 1) {

				if (ch == ('0')) { // '0' erase down index
					if (!doMinimizeDown())
						rejectIndicators[0]++;
				}
				else { // '1' erase up index, no other choice
					if (!doMinimizeUp())
						rejectIndicators[1]++;
				}
			}
			else
				break; // if rejectIndicators = [1,1]
		}

		if (minimized.length() < minLength) {
			minLength = minimized.length();
			betterResultsMap.put(minimized.length(), minimized);

		}

	}

	private static int[][] multiArrayClone(int[][] multiArray) {
		return multiArray != null ? Arrays.stream(multiArray).map(int[]::clone).toArray(int[][]::new) : null;
	}

	public static String MinWindowSubstring(String[] strArr) {
		final String N = strArr[0];
		final String K = strArr[1];
		minimized = N_BASE = N;
		String result = "";
		minLength = Integer.MAX_VALUE;

		K_MATRIX_BASE = getCharCounter(K);
		K_MATRIX_N_BASE = getTargetInSource(N);
		K_matrix_N_oper = multiArrayClone(K_MATRIX_N_BASE);
		betterResultsMap = new HashMap<>();

		buildUpDownCombination(getCombinationsDepth(getCharCounter(N), K_MATRIX_BASE), new StringBuffer());

		result = betterResultsMap.get(Collections.min(betterResultsMap.keySet())); // minimized;

		LOGGER.log(Level.WARNING, " -- betterResultsMap=" + betterResultsMap.toString());

		return result;
	}

	public static void main(String[] args) {

		doBigRun();
	}

	private static void doBigRun() {
		String[] strArrMn = new String[] {
				"aaffsfsfasfasfasfasfasfacasfafe", "fafsf"
		}; // affsf

		System.out.println("MinWindowSubstring started. [N,K]=["
				+ strArrMn[0] + "," + strArrMn[1] + "]");

		long start = System.nanoTime();
		String result = MinWindowSubstring(strArrMn);
		long finish = System.nanoTime();

		System.out.println(String.format("The smallest substring of N that contains all the "
				+ " characters in K is: \"%s\" ", result));

		long milion = 1_000_000_000;
		long tempSec = (finish - start) / milion;
		long sec = tempSec % 60;
		long min = (tempSec / 60) % 60;
		long hour = (tempSec / (60 * 60)) % 24;

		System.out.println("Time elapsed - " + "hr:" + hour + ",min:" + min + ",sec:" + sec);
	}

	@Test
	public void doTests01() throws ComparisonFailure {
		strArr = new String[] {
				"ahffaksfajeeubsne", "jefaa"
		}; // aksfaje

		assertEquals(MinWindowSubstring(strArr), ("aksfaje"));

	}

	@Test
	public void doTests02() throws ComparisonFailure {
		strArr = new String[] {
				"aaffhkksemckelloe", "fhea"
		}; // affhkkse

		assertEquals(MinWindowSubstring(strArr), ("affhkkse"));

	}

	@Test
	public void doTests03() throws ComparisonFailure {
		strArr = new String[] {
				"aabdccdbcacd", "aad"
		}; // aabd

		assertEquals(MinWindowSubstring(strArr), ("aabd"));

	}

	@Test
	public void doTests05() throws ComparisonFailure {
		strArr = new String[] {
				"aaabaaddae", "aed"
		}; // dae

		assertEquals(MinWindowSubstring(strArr), ("dae"));

	}

	@Test
	public void doTests06() throws ComparisonFailure {
		strArr = new String[] {
				"aaffsfsfasfasfasfasfasfacasfafe", "fafe"
		}; // fafe

		assertEquals(MinWindowSubstring(strArr), ("fafe"));

	}

}
