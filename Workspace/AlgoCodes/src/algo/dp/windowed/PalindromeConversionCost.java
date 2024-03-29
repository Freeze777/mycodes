package algo.dp.windowed;

import java.util.Scanner;

public class PalindromeConversionCost {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tst = sc.nextInt();

		while (tst > 0) {
			int in = sc.nextInt(), d = sc.nextInt(), r = sc.nextInt();
			char c[] = sc.next().toCharArray();
			int n = c.length;
			int t[][] = new int[3][c.length];
			int dp[][] = new int[c.length][c.length];

			// for all window size greater than 4
			for (int window = 1; window < n; window++) {
				for (int start = 0; start < n - window; start++) {
					// for all window size= i
					if (c[start] == c[start + window]) {
						dp[start][start + window] = dp[start + 1][start
								+ window - 1];
						// t[(j+j+i)%3][j+i]
					} else {
						int temp = Math.min(dp[start + 1][start + window - 1]
								+ r, Math.min(dp[start + 1][start + window]
								+ in, dp[start][start + window - 1] + d));
						int temp1 = Math.min(
								dp[start][start + window - 1] + in,
								dp[start + 1][start + window] + d);
						dp[start][start + window] = Math.min(temp, temp1);
					}

				}
			}
			System.out.println("Case:" + dp[0][n - 1]);
			tst--;
		}

	}

}
