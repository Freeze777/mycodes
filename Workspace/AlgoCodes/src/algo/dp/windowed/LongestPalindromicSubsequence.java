package algo.dp.windowed;

import algo.Utils;

public class LongestPalindromicSubsequence {
	public static void main(String[] args) {
		//String s = "aghbxcycmsbka";// abcycba is the answer
		String s="azbzczdzez";
		int ans = LPS(s);
		System.out.println(ans);

	}

	public static int LPS(String s) {
		int[][] dp = new int[s.length()][s.length()];
		for (int i = 0; i < dp.length - 1; i++) {
			dp[i][i] = 1;
			if (s.charAt(i) == s.charAt(i + 1))
				dp[i][i + 1] = 2;
			else
				dp[i][i + 1] = 1;
		}
		dp[dp.length - 1][dp.length - 1] = 1;
		for (int w = 2; w < dp.length; w++) {
			for (int start = 0; start < dp.length - w; start++) {
				if (s.charAt(start) == s.charAt(start + w))
					dp[start][start + w] = 2 + dp[start + 1][start + w - 1];// window
																			// size
																			// =
																			// w-2
				else
					dp[start][start + w] = Math.max(dp[start][start + w - 1],
							dp[start + 1][start + w]);// window size = w-1
			}
		}
		// Utils.printArray(dp);
		return dp[0][dp.length - 1];
	}
}
