package find.intersection;

import java.util.Arrays;
import java.util.TreeSet;
import static java.util.stream.Collectors.toCollection;

/**
 * CODERBYTE
 * 
 * Find Intersection
 * 
 * Have the function FindIntersection(strArr) read the array of strings stored
 * in strArr which will contain 2 elements: the first element will represent a
 * list of comma-separated numbers sorted in ascending order, the second element
 * will represent a second list of comma-separated numbers (also sorted). Your
 * goal is to return a comma-separated string containing the numbers that occur
 * in elements of strArr in sorted order. If there is no intersection, return
 * the string false.
 * 
 * Examples
 * 
 * Input: new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"} Output: 1,4,13
 * 
 * Input: new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"} Output: 1,9,10
 *
 */
public class Solution {

	public static String FindIntersection(String[] strArr) {
		TreeSet<Integer> set1 = getSet(strArr, 0);
		TreeSet<Integer> set2 = getSet(strArr, 1);
		set1.retainAll(set2); // retains all elements included in argument collection

		return (set1.isEmpty() ? "false"
				: Arrays.toString(new TreeSet<>(set1).toArray()).replaceAll("[\\[\\](){}]","").replaceAll("\\s+",""));
	}

	private static TreeSet<Integer> getSet(String[] strArr, int i) {
		return Arrays.stream(strArr[i].replaceAll("\\s+","").split(",")).map(Integer::parseInt)
				.collect(toCollection(TreeSet::new));
	}

	public static void main(String[] args) {

		String[] strArr = new String[] { "1, 3, 4, 7, 13", "1, 2, 4, 13, 15" }; // Output: 1,4,13
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "2, 3, 4", "3" }; // Output: 3
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "1, 3, 9, 10, 17, 18", "1, 4, 9, 10" }; // Output: 1,9,10
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "2, 5, 7, 10, 11, 12", "2, 7, 8, 9, 10, 11, 15" }; // Output: 2,7,10,11
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "1, 2, 3, 4, 5", "6, 7, 8, 9, 10" }; // Output: false
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "1, 2, 4, 5, 6, 9", "2, 3, 4, 8, 10" }; // Output: 2,4
		System.out.println(FindIntersection(strArr));

		strArr = new String[] { "21, 22, 23, 25, 27, 28", "21, 24, 25, 29" }; // Output: 21,25
		System.out.println(FindIntersection(strArr));

	}

}
