package algo.dp;

import java.util.Scanner;

/**
 * O(n^2) algorithm
 */
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int A[] = new int[n];
		int dp[] = new int[n];

		for (int i = 0; i < A.length; i++) {
			A[i] = sc.nextInt();
			dp[i] = 1;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j])
					dp[i] = Math.max(dp[j] + 1, dp[i]);
			}
			max = Math.max(dp[i], max);
		}
		System.out.println(max);
	}
}
