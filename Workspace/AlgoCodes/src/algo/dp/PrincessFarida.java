package algo.dp;

import java.util.Scanner;
/*Princess Farida : SPOJ
3
5
1 2 3 4 5
0

1
10
 * */
public class PrincessFarida {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int c=1;
		while (c <= t) {
			int n = sc.nextInt();
			long ans = 0;
			long[] dp = new long[n];
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextLong();
			}
			if (n == 0)
				ans = 0;
			else if (n == 1)
				ans = arr[0];
			else {
				dp[0] = arr[0];
				dp[1] = Math.max(arr[0], arr[1]);
				for (int i = 2; i < dp.length; i++) {
					dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
				}
				ans=dp[n-1];
			}
			System.out.println("Case "+c+": "+ans);
			c++;
		}
	}

}
