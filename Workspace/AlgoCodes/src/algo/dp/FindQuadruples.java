package algo.dp;

public class FindQuadruples {
	/*
	 * getCountAllAbove1(S) Given a number “S”, find out how many number of ways
	 * a, b, c, d (all > 1) can exist such that a+b+c+d = S;
	 */
	/*
	 * getCountIncreasingQuadruples(S) given a number N. print in how many ways
	 * it can be represented as N = a+b+c+d , 1< =a< =b< =c< = d; 1<=N< = 5000
	 */
	public static void main(String[] args) {
		int s = 2055;
		//System.out.println(getCountAllAbove1(s));
		System.out.println(getCountIncreasingQuadruples(s));
		System.out.println(getCountIncreasingQuadruplesDP(s));
	}

	private static long getCountIncreasingQuadruples(int s) {
		long count = 0;
		for (int a = 1; a <= s; a++) {
			for (int b = a; b <= s - a; b++) {
				for (int c = b; c <= s - a - b; c++) {
					int d = s - a - b - c;
					if (d >= c) {
						// System.out.println(a + " " + b + " " + c + " " + d);
						count++;
					}
				}
			}
		}
		return count;
	}

	private static long getCountIncreasingQuadruplesDP(int s) {
		int k=4;
		long[][] dp = new long[s + 1][k+1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][1] = 1;
		}

		for (int n = 2; n <= s; n++) {
			for (int j = 2; j <= k; j++) {
				if (n >= j) {
					dp[n][j] = dp[n - j][j] + dp[n - 1][j - 1];
				}
			}
		}
		return dp[s][k];
	}

	private static long getCountAllAbove1(int s) {
		long count = 0;
		for (int a = 2; a <= s; a++) {
			for (int b = 2; b <= s - a; b++) {
				for (int c = 2; c <= s - a - b; c++) {
					int d = s - a - b - c;
					if (d > 1) {
						// System.out.println(a+" "+b+" "+c+" "+d);
						count++;
					}
				}
			}
		}

		return count;
	}
}
