package streams.arrays.to.collection;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

	public static String convertArrays(String[] strArr) {
		String result = "";

		List<List<Integer>> listlist = new ArrayList<>();
		for (String a : strArr) {
			List<Integer> list = Arrays.stream(a.replaceAll("\\s+", "").split(",")).map(Integer::parseInt)
					.collect(toCollection(ArrayList::new));
			System.out.println("list=" + list.toString());
			listlist.add(list);
		}
		System.out.println("listlist=" + listlist.toString());
		// .replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", ""));

		Set<Set<Integer>> setSet = new HashSet<>();
		for (String a : strArr) {
			Set<Integer> set = Arrays.stream(a.replaceAll("\\s+", "").split(",")).map(Integer::parseInt)
					.collect(toCollection(HashSet::new));
			System.out.println("set=" + set.toString());
			setSet.add(set);
		}
		System.out.println("setSet=" + setSet.toString());
		// .replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", ""));

		Set<Set<Integer>> tsetSet = new HashSet<>();
		for (String a : strArr) {
			TreeSet<Integer> tset = Arrays.stream(a.replaceAll("\\s+", "").split(",")).map(Integer::parseInt)
					.collect(toCollection(TreeSet::new));
			System.out.println("tset=" + tset.toString());
			tsetSet.add(tset);
		}
		System.out.println("tsetSet=" + tsetSet.toString());
		// .replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", ""));

		// enables to avoid duplicates
		Set<Set<Integer>> ttsetSet = new TreeSet<>
		// public int compare method must be overridden cause TreeSet does not implement
		// Comparable interface
		(Comparator.comparing((Set<Integer>::size)));
		for (String a : strArr) {
			TreeSet<Integer> ttset = Arrays.stream(a.replaceAll("\\s+", "").split(",")).map(Integer::parseInt)
					.collect(toCollection(TreeSet::new));
			System.out.println("ttset=" + ttset.toString());
			ttsetSet.add(ttset);
		}
		System.out.println("ttsetSet=" + ttsetSet.toString());
		// .replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", ""));

		// remembers order
		Set<Set<Integer>> lhsetSet = new LinkedHashSet<>();
		for (String a : strArr) {
			Set<Integer> lhset = Arrays.stream(a.replaceAll("\\s+", "").split(",")).map(Integer::parseInt)
					.collect(toCollection(LinkedHashSet::new));
			System.out.println("lhset=" + lhset.toString());
			lhsetSet.add(lhset);
		}
		System.out.println("lhsetSet=" + lhsetSet.toString());
		// .replaceAll("[\\[\\](){}]", "").replaceAll("\\s+", ""));

		return result;
	}

	public static void main(String[] args) {

		String[] strArr = new String[] { "19, 3, 4, 7, 13,  13", "19, 2, 4, 13, 15, 15" }; // Output: 1,4,13
		System.out.println(convertArrays(strArr));

	}

}
