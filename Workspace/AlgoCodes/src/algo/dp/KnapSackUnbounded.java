package algo.dp;

import java.util.Arrays;
import java.util.Scanner;

public class KnapSackUnbounded {
	static long dp[];
	static int A[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			A = new int[n];
			dp = new long[k + 1];

			for (int i = 0; i < A.length; i++)
				A[i] = sc.nextInt();

			Arrays.sort(A);
			long value = getMaxValue(k);
			System.out.println(value);
			tst--;
		}

	}

	private static long getMaxValue(int residue) {

		if (residue < A[0]) {
			dp[residue] = 0;
		} else if (dp[residue] != 0) {
			// value already computed
		} else {
			for (int i = 0; i < A.length; i++) {
				if (residue < A[i])
					break;
				else if (residue % A[i] == 0) {
					dp[residue] = residue;
					break;
				} else if (residue >= A[i]) {

					dp[residue] = Math.max(A[i] + getMaxValue(residue - A[i]),
							dp[residue]);
				}
			}

		}
		return dp[residue];
	}
}
