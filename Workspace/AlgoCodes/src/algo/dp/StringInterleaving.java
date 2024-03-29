package algo.dp;

public class StringInterleaving {
	public boolean isInterleave(String s1, String s2, String s3) {
		if (s3.length() != (s1.length() + s2.length()))
			return false;
		/* s1 in the row ,s2 in the column */
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;

		/* only s1 is given and s2 is empty i.e checking first i characters of s3 and s1 are interleavings*/
		for (int i = 1; i < dp.length; i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1))//bug fix: silly..!
				dp[i][0] = dp[i - 1][0];
		}
		/* only s2 is given and s1 is empty i.e checking first j characters of s3 and s2 are interleavings*/
		for (int j = 1; j < dp[0].length; j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1))
				dp[0][j] = dp[0][j - 1];

		}
		/*
		 * dp[i][j] -- we consider first i characters of s1 and j characters of
		 * s2 and i+j characters of s3
		 */
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (s3.charAt(i + j - 1) == s1.charAt(i - 1)) {
					dp[i][j] = dp[i - 1][j];
				}
				if (s3.charAt(i + j - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i][j] || dp[i][j - 1];// bug fix : need to || else we will miss the ans of the above if loop
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}
}
