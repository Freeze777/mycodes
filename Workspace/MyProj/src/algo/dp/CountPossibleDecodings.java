package algo.dp;

public class CountPossibleDecodings {
	public static void main(String[] args) {
String s="0";
char[] sequence =s.toCharArray();
		//		char[] sequence = { '2', '2', '3', '4' };

		int count = countDecodings_DP(sequence);
		System.out.println(count);
		count = countDecodings(sequence, sequence.length);
		System.out.println(count);
	}

	private static int countDecodings_DP(char[] sequence) {
		int dp[] = new int[sequence.length + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i < dp.length; i++) {
			if (sequence[i - 1] > '0')
				dp[i] += dp[i - 1];
			int t = (sequence[i - 2] - '0') * 10 + (sequence[i - 1] - '0');
			if (t <= 26)
				dp[i] += dp[i - 2];
		}
		
		return dp[sequence.length];
	}

	public static int countDecodings(char[] sequence, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 1;
		}
		int count = 0;
		if (sequence[n - 1] > '0') {
			count = countDecodings(sequence, n - 1);// sequence[n-1] can be
													// decoded into 1 character
		}
		int t = (sequence[n - 2] - '0') * 10 + (sequence[n - 1] - '0');
		if (t <= 26) {
			count += countDecodings(sequence, n - 2);
		}
		return count;

	}

}
