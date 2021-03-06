package algo.dp;

/*How many edits would it take to convert s2 to s1*/
public class MinimumEditDistance {
	public static void main(String[] args) {
		String s1 = "abcdef", s2 = "azced";
		// including empty strings and s1 in the columns and s2 along the rows
		int[][] dp = new int[s2.length() + 1][s1.length() + 1];
		// minimum edits to convert an empty string to s1
		for (int i = 0; i < dp[0].length; i++)
			dp[0][i] = i;
		// minimum edits to convert s2 to an empty string
		for (int i = 0; i < dp.length; i++)
			dp[i][0] = i;
		for (int j = 1; j < dp[0].length; j++) {// j traverses s1
			for (int i = 1; i < dp.length; i++) {// i traverses s2
				if (s2.charAt(i - 1) == s1.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1]; // no edits needed..copy
													// previous edits
				else {// dp[i-1][j]=deleted ith character in s2
						// dp[i][j-1]=deleted jth character in s1
						// dp[i-1][j-1]=changing character s2[i] to make both
						// s2[i]==s1[j]
					dp[i][j] = Math.min(1 + dp[i - 1][j],
							Math.min(1 + dp[i][j - 1], 1 + dp[i - 1][j - 1]));
				}
			}
		}
		System.out.println(dp[s2.length()][s1.length()]);
	}
}
