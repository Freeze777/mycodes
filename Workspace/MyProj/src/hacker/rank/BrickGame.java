package hacker.rank;

import java.util.Scanner;

class Profit {
	long first = 0;
	long second = 0;

}

public class BrickGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();
		while (tst > 0) {
			int n = sc.nextInt();
			int A[] = new int[n];
			Profit dp[][] = new Profit[n][n];
			for (int i = 0; i < n; i++) {
				A[i] = sc.nextInt();
			}

			for (int i = 0; i < n; i++) {
				Profit pr0 = new Profit();
				dp[i][i] = pr0;
				dp[i][i].first = A[i];
				dp[i][i].second = 0;
				if (i + 1 < n) {
					Profit pr1 = new Profit();
					dp[i][i + 1] = pr1;
					dp[i][i + 1].first = A[i] + A[i + 1];
					dp[i][i + 1].second = 0;
				}
				if (i + 2 < n) {
					Profit pr2 = new Profit();
					dp[i][i + 2] = pr2;
					dp[i][i + 2].first = A[i] + A[i + 1] + A[i + 2];
					dp[i][i + 2].second = 0;
				}
				if (i + 3 < n) {// 4 consecutive elements
					Profit pr3 = new Profit();
					dp[i][i + 3] = pr3;
					dp[i][i + 3].first = A[i] + A[i + 1] + A[i + 2];
					dp[i][i + 3].second = A[i + 3];
				}
			}
			// for all window size greater than 4
			for (int i = 4; i < n; i++) {
				for (int j = 0; j < n - i; j++) {
					// for all window size= i
					dp[j][j + i] = new Profit();
					
					dp[j][j + i].first = Math.max(
							Math.max(A[j] + dp[j + 1][j + i].second, A[j]
									+ A[j + 1] + dp[j + 2][j + i].second), A[j]
									+ A[j + 1] + A[j + 2]
									+ dp[j + 3][j + i].second);
					
					if (dp[j][j + i].first == A[j] + dp[j + 1][j + i].second)
						dp[j][j + i].second = dp[j + 1][j + i].first;
					else if (dp[j][j + i].first == A[j] + A[j + 1]
							+ dp[j + 2][j + i].second)
						dp[j][j + i].second = dp[j + 2][j + i].first;
					else
						dp[j][j + i].second = dp[j + 3][j + i].first;

				}
			}
			System.out.println(dp[0][n - 1].first);

			tst--;
		}

	}
}
