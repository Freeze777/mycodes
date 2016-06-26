import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * O(n^2) algorithm
 */
public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<Integer> a = new ArrayList<Integer>();
		int n = -1;
		while ((n = sc.nextInt()) != 0)
			a.add(n);

		int[] dp = new int[a.size()];
		Integer[] A = new Integer[a.size()];
		a.toArray(A);

		ArrayList<Integer> l = new  ArrayList<Integer>();
		l.add(A[0]);

		for (int i = 0; i < A.length; i++)
			dp[i] = 1;

		int max = Integer.MIN_VALUE;
		StringBuilder ans = new StringBuilder();
		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);

				}

			}

			if (max < dp[i]) {
				max = dp[i];
				l.add(A[i]);
				
			}
		}
		for (int t:l)
			System.out.print(t+" ");
	}
}