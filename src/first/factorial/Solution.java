package first.factorial;

/**
 * 
 * CODERBYTE
 * 
 * First Factorial
 * 
 * Have the function FirstFactorial(num) take the num parameter being passed and
 * return the factorial of it. For example: if num = 4, then your program should
 * return (4 * 3 * 2 * 1) = 24. For the test cases, the range will be between 1
 * and 18 and the input will always be an integer.
 * 
 * Examples
 * 
 * Input: 4 Output: 24
 * 
 * Input: 8 Output: 40320
 * 
 * Input: 7 Output: 5040
 *
 */
public class Solution {

	public static int FirstFactorial(int num) {
		int fact = 1;

		for (int i = 2; i <= num; i++)
			fact *= i;

		return fact;
	}

	public static void main(String[] args) {
		int num = 0;

		num = 3;
		System.out.println(FirstFactorial(num));

		num = 4;
		System.out.println(FirstFactorial(num));

		num = 7;
		System.out.println(FirstFactorial(num));

		num = 8;
		System.out.println(FirstFactorial(num));

		num = 9;
		System.out.println(FirstFactorial(num));
		
		num = 10;
		System.out.println(FirstFactorial(num));

	}

}
