package first.reverse;

/**
 * CODERBYTE
 * 
 * First Reverse
 * 
 * Have the function FirstReverse(str) take the str parameter being passed and
 * return the string in reversed order. For example: if the input string is
 * "Hello World and Coders" then your program should return the string sredoC
 * dna dlroW olleH.
 * 
 * Examples
 * 
 * Input: "coderbyte" Output: etybredoc
 * 
 * Input: "I Love Code" Output: edoC evoL I
 *
 */
public class Solution {

	public static String FirstReverse(String str) {

//		return new StringBuilder().append(str).reverse().toString();
		return new StringBuffer().append(str).reverse().toString();
	}

	public static void main(String[] args) {

		String str = "";

		str = "coderbyte";
		System.out.println(FirstReverse(str));

		str = "I Love Code";
		System.out.println(FirstReverse(str));

	}

}
