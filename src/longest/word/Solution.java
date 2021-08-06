package longest.word;

import java.util.regex.Pattern;

/**
 * 
 * CODERBYTE
 * 
 * Longest Word
 * 
 * Have the function LongestWord(sen) take the sen parameter being passed and
 * return the longest word in the string. If there are two or more words that
 * are the same length, return the first word from the string with that length.
 * Ignore punctuation and assume sen will not be empty. Words may also contain
 * numbers, for example "Hello world123 567"
 * 
 * Examples
 * 
 * Input: "fun&!! time" Output: time
 * 
 * Input: "I love dogs" Output: love
 *
 */
public class Solution {

	public static String LongestWord(String sen) {
		String longest = "";
		int lngth = -1;
		String regex = "^[a-zA-Z0-9]+$";
		String[] splited = sen.split("\\s+");
//		System.out.println(" -- " + Arrays.toString(splited));

		for (String word : splited) {
			if (Pattern.matches(regex, word))
				if (lngth < word.length()) { // then only first found if equals length
					lngth = word.length();
					longest = word;
				}
		}

		return longest;
	}

	public static void main(String[] args) {

		String sen = "";

		sen = "fun&!! time";
		System.out.println(LongestWord(sen));

		sen = "I love abds345  dogs";
		System.out.println(LongestWord(sen));

		sen = "I love 123.,ft  dogs for  ever";
		System.out.println(LongestWord(sen));

	}

}
