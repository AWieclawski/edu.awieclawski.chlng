package codeland.username.validation;

import java.util.regex.Pattern;

/**
 * CODERBYTE
 * 
 * Codeland Username Validation
 * 
 * Have the function CodelandUsernameValidation(str) take the str parameter
 * being passed and determine if the string is a valid username according to the
 * following rules:
 * 
 * 1. The username is between 4 and 25 characters.
 * 
 * 2. It must start with a letter.
 * 
 * 3. It can only contain letters, numbers, and the underscore character.
 * 
 * 4. It cannot end with an underscore character.
 * 
 * If the username is valid then your program should return the string true,
 * otherwise return the string false.
 * 
 * Examples
 * 
 * Input: "aa_" Output: false
 * 
 * Input: "u__hello_world123" Output: true
 */
public class Solution {

	public static String CodelandUsernameValidation(String str) {
		String result = "false";
		String regex = "[a-zA-Z][a-zA-Z0-9_]{2,23}[^_]";

		if (Pattern.matches(regex, str))
			result = "true";
		return result;
	}

	public static void main(String[] args) {

		String str = "";

		str = "aa_";
		System.out.println(CodelandUsernameValidation(str)); // false

		str = "u__hello_world123";
		System.out.println(CodelandUsernameValidation(str)); // true

		str = "1dfr45thy6_j";
		System.out.println(CodelandUsernameValidation(str)); // false

	}

}
