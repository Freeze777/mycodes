package algo.dp;
import java.util.Scanner;
/*HackerRank*/
public class Clouds {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int c[] = new int[n];
		int dp[] = new int[n];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			c[i] = in.nextInt();

			if (c[i] == 0) {

				if (i >= 2) {
					if (c[i - 2] == 0 && c[i - 1] == 0)
						dp[i] = Math.min(dp[i - 1], dp[i - 2]) + 1;
					else if (c[i - 2] == 1 && c[i - 1] == 0) {
						dp[i] = dp[i - 1] + 1;
					} else if (c[i - 2] == 0 && c[i - 1] == 1) {
						dp[i] = dp[i - 2] + 1;
					}

				} else if (i >= 1) {
					if (c[i - 1] == 0)
						dp[i] = dp[i - 1] + 1;

				}

			}

		}
		System.out.println(dp[n - 1]);

	}
}
