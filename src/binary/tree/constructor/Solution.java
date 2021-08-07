package binary.tree.constructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * CODERBYTE
 * 
 * Tree Constructor
 * 
 * Have the function TreeConstructor(strArr) take the array of strings stored in
 * strArr, which will contain pairs of integers in the following format:
 * (i1,i2), where i1 represents a child node in a tree and the second integer i2
 * signifies that it is the parent of i1. For example: if strArr is ["(1,2)",
 * "(2,4)", "(7,2)"], then this forms the following tree:
 * 
 * please find illustration in: binary.tree.constructor.img
 * 
 * which you can see forms a proper binary tree. Your program should, in this
 * case, return the string true because a valid binary tree can be formed. If a
 * proper binary tree cannot be formed with the integer pairs, then return the
 * string false. All of the integers within the tree will be unique, which means
 * there can only be one node in the tree with the given integer value.
 * 
 * Examples
 * 
 * Input: new String[] {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"} Output:
 * true
 * 
 * Input: new String[] {"(1,2)", "(3,2)", "(2,12)", "(5,2)"} Output: false
 *
 */
public class Solution {

	public static String masterTreeConstructor(String[] strArr) {
		Set<String> children = new HashSet<>();
		Map<String, Integer> parents = new HashMap<>();
		// Build a Set containing the child nodes, which will remove duplicates, and a
		// Map of the parent nodes that
		// keeps track of the count of each parent.
		for (String node : strArr) {
			String[] values = node.replaceAll("[^0-9,]", "").split(",");
			children.add(values[0]);
			Integer countParents = parents.get(values[1]);
			// If a parent node has more than two child nodes, the tree is non-binary
			if (countParents != null && countParents + 1 > 2) {
				return "false";
			} else {
				parents.put(values[1], (countParents == null ? 1 : ++countParents));
			}
		}
		// If the size of the children Set, is less than the length of the input array,
		// it means that the same child has
		// more than one parent, which makes the tree non-binary.
//		System.out.println("childSize=" + children.size() + ",arrLngth=" + strArr.length);
		return "" + (children.size() == strArr.length);
	}

	public static String TreeConstructor(String[] strArr) {
		int[] parents = new int[99]; // limited to checking only two-digit numbers
		int[] children = new int[99]; // limited to checking only two-digit numbers

		for (String a : strArr) {
			Integer[] pair = Arrays.stream(a.replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", "").split(","))
					.map(Integer::parseInt).toArray(Integer[]::new);
			parents[pair[1]]++;
			children[pair[0]]++;

			if (parents[pair[1]] > 2 || children[pair[0]] > 1)
				return "false";
		}

		return "true";
	}

	public static void main(String[] args) {
		String[] strArr = null;

		strArr = new String[] { "(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)" }; // true
		System.out.println(TreeConstructor(strArr));

		strArr = new String[] { "(1,2)", "(3,2)", "(2,12)", "(5,2)" }; // false
		System.out.println(TreeConstructor(strArr));

		strArr = new String[] { "(2,5)", "(2,6)" }; // false
		System.out.println(TreeConstructor(strArr));

		strArr = new String[] { "(2,3)", "(1,2)", "(4,9)", "(9,3)", "(12,9)", "(6,4)", "(1,9)" }; // false
		System.out.println(TreeConstructor(strArr));

		strArr = new String[] { "(5,6)", "(6,3)", "(2,3)", "(12,5)" }; // true
		System.out.println(TreeConstructor(strArr));

		strArr = new String[] { "(1,2)", "(2,4)", "(7,4)" }; // true
		System.out.println(TreeConstructor(strArr));
		
		strArr = new String[] {"(10,20)", "(20,50)"}; // true
		System.out.println(TreeConstructor(strArr));

	}

}
