package algo.dp;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String str) {
		int n = str.length();
		if (n == 0)
			return 0;
		int[] lastIndices = new int[256];// last index of each character
		int[] dp = new int[n];
		int max = 1;
		Arrays.fill(lastIndices, -1);
		Arrays.fill(dp, 0);
		dp[0] = 1;
		lastIndices[str.charAt(0)] = 0;
		for (int i = 1; i < n; i++) {
			char c = str.charAt(i);
			if (lastIndices[c] < i - dp[i - 1])
				dp[i] = dp[i - 1] + 1;
			else
				dp[i] = i - lastIndices[c];

			max = Math.max(max, dp[i]);
			lastIndices[c] = i;
		}
		return max;
	}
}
