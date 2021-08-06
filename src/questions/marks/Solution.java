package questions.marks;

/**
 * CODERBYTE
 * 
 * Questions Marks 
 * 
 * Have the function QuestionsMarks(str) take the str string
 * parameter, which will contain single digit numbers, letters, and question
 * marks, and check if there are exactly 3 question marks between every pair of
 * two numbers that add up to 10. If so, then your program should return the
 * string true, otherwise it should return the string false. If there aren't any
 * two numbers that add up to 10 in the string, then your program should return
 * false as well.
 * 
 * For example: if str is "arrb6???4xxbl5???eee5" then your program should
 * return true because there are exactly 3 question marks between 6 and 4, and 3
 * question marks between 5 and 5 at the end of the string.
 * 
 * Examples Input: "aa6?9" Output: false
 * 
 * Input: "acc?7??sss?3rr1??????5" Output: true
 * 
 * 
 */
public class Solution {
	final static char QM = '?';
	final static String TR = "true";
	final static String FLS = "false";
	private static int first;
	private static int last;
	private static int qmCnt;
	private static String result;

	private static String lastAssign(char ch) {
		String x = Character.toString(ch);
		last = Integer.valueOf(x);
		if (qmCountChck())
			result = TR;
		first = -1;
		qmCnt = 0;
		return result;
	}

	private static void firstAssign(char ch) {
		String x = Character.toString(ch);
		first = Integer.valueOf(x);
	}

	private static boolean qmCountChck() {
		if (qmCnt == 3 && (last + first) == 10) {
//			System.out.println("first=" + first + ",last=" + last + ",qmCnt=" + qmCnt);
			return true;
		}
		return false;
	}

	public static String QuestionsMarks(String str) {

		int lngth = str.length();
		boolean isDigit = false;

		result = FLS;
		first = -1;
		last = -1;
		qmCnt = 0;

		for (int i = 0; i < lngth; i++) {

			char ch = str.charAt(i);

			isDigit = Character.isDigit(ch);

			if (isDigit && first < 0) {
				firstAssign(ch);
				continue;
			}

			if (isDigit && first > 0) {
				result = lastAssign(ch);
				if (result.equals(TR))
					break;
				else
					continue;
			}

			if (ch == QM && first > 0) {
				qmCnt++;
			}

		}

		return result;
	}

	public static void main(String[] args) {

		String str = "aa6?9";
		System.out.println(str + "|" + QuestionsMarks(str));

		str = "acc?7??sss?3rr1??????5";
		System.out.println(str + "|" + QuestionsMarks(str));

	}

}
