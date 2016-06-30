package algo.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public static void main(String[] args) {
		String s = "1234";
		char[] c = s.toCharArray();
		//long startTime = System.nanoTime();
		// printPermutations_method1(c, 0, s.length() - 1);
		System.out.println(printPermutations_method3(s));
		// printPermutations_method2(c);
		//long estimatedTime = System.nanoTime() - startTime;
		//System.out.println(estimatedTime);
		// method3:1650152 ns
		// method2:1352995 ns
		// method1:1442457 ns

	}
	
	private static List<String> printPermutations_method3(String s) {
		List<String> permutations = new ArrayList<>();
	/*	if (s.length() == 0)
			return l;*/
		if (s.length() == 1) {
			permutations.add(s);
			return permutations;
		}
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			String left = s.substring(0, i);
			String right = s.substring(i + 1, s.length());
			List<String> sub_permutations = printPermutations_method3(left + right);
			for (String t : sub_permutations)
				permutations.add(t + ch);
		}
		
		return permutations;
	}

	public static void printPermutations_method1(char[] s, int depth, int n) {

		if (depth == n) {
			System.out.println(String.valueOf(s));
			return;
		}
		for (int i = depth; i <= n; i++) {
			char temp = s[depth];
			s[depth] = s[i];
			s[i] = temp;
			printPermutations_method1(s, depth + 1, n);
			s[i] = s[depth];
			s[depth] = temp;
		}

	}

	private static void printPermutations_method2(char[] c) {
		String init = String.valueOf(c);
		do {
			System.out.println(String.valueOf(c));
			findNextPermutation(c);
		} while (!init.equals(String.valueOf(c)));

	}

	private static void findNextPermutation(char[] arr) {
		int i;

		for (i = arr.length - 1; (i >= 1) && (arr[i] <= arr[i - 1]); i--)
			;
		int pivot = i - 1;
		// System.out.println(pivot);
		if (pivot >= 0) {
			/* finding element just greater than A[pivot] */
			for (i = arr.length - 1; (i > pivot) && (arr[pivot] >= arr[i]); i--)
				;

			char temp = arr[i];
			arr[i] = arr[pivot];
			arr[pivot] = temp;

		}
		// to cycle back "4321"--->"1234"
		for (int x = pivot + 1, y = arr.length - 1; x < y; x++, y--) {
			char temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
		}

	}

}
