package hacker.rank.project.euler;

import java.util.Scanner;

public class TraingleGraphMaxSumPath {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();

		while (tst > 0) {
			int n = sc.nextInt();
			int size = (n * (n + 1)) / 2;
			int[] triangle = new int[size];
			int[] dp = new int[size];

			for (int i = 0; i < triangle.length; i++) {
				triangle[i] = sc.nextInt();
			}

			dp[0] = triangle[0];
			int max = dp[0];

			for (int row = 1; row < n; row++) {
				int row_start = ((row - 1) * (row)) / 2;
				int row_end = (((row + 1) * (row)) / 2) - 1;
				for (int i = row_start; i <= row_end; i++) {
					dp[i + row] = Math.max(dp[i + row], triangle[i + row]
							+ dp[i]);
					dp[i + row + 1] = Math.max(dp[i + row + 1], triangle[i
							+ row + 1]
							+ dp[i]);

					max =Math.max(max, Math.max(dp[i + row], dp[i + row + 1]));

				}

			}
			System.out.println(max);
			
			tst--;
		}
	}
}
