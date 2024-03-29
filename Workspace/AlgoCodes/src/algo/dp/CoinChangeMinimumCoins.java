package algo.dp;

import java.util.Arrays;

/*I thot i knew this shit...!!! But NO...!!! Never coded it and got fucked up..!*/
public class CoinChangeMinimumCoins {
	public class Solution {
		// O(mn) time and O(n) space complexity
		public int coinChange_method3(int[] coins, int amount) {
			if (amount == 0)
				return 0;
			int[][] dp = new int[2][amount + 1];
			for (int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				dp[i][0] = 0;
			}
			for (int j = 1; j <= amount; j++) {
				if (j % coins[0] == 0)
					dp[0][j] = j / coins[0];
			}
			for (int i = 1; i < coins.length; i++) {
				for (int j = 1; j <= amount; j++) {
					dp[i % 2][j] = dp[(i - 1) % 2][j];
					if (j >= coins[i]
							&& dp[i % 2][j - coins[i]] != Integer.MAX_VALUE)
						dp[i % 2][j] = Math.min(dp[i % 2][j], 1 + dp[i % 2][j
								- coins[i]]);
				}
			}
			return dp[(coins.length - 1) % 2][amount] == Integer.MAX_VALUE ? -1
					: dp[(coins.length - 1) % 2][amount];
		}
	}

	// O(mn) time ..but O(n) space
	public int coinChange_method2(int[] coins, int amount) {
		if (amount == 0)
			return 0;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);// Most important line... learnt it
											// with pain!
		dp[0] = 0;// amount=0 need no coins
		for (int i = 0; i < coins.length; i++) {
			for (int j = coins[i]; j <= amount; j++) {
				if (dp[j - coins[i]] != Integer.MAX_VALUE)// bug: had to add
															// this.. otherwise
															// 1+MAX_VALUE will
															// be come -ve and
															// evrthg gets
															// fucked up
					dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}

	// O(mn) space and time
	public int coinChange_method1(int[] coins, int amount) {
		if (amount == 0)
			return 0;
		// Arrays.sort(coins);// is this really needed??? NOOOOOO...!!! not in
		// this approach
		int[][] dp = new int[coins.length][amount + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);// Most important line...
													// learnt it with pain!
			dp[i][0] = 0;// amount=0 need no coins
		}
		// filling up the first row..
		for (int j = 0; j <= amount; j++) {
			if (j % coins[0] == 0)
				dp[0][j] = j / coins[0];
		}
		// cant we do it with just two row?? Well actually we need just one..!!
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j <= amount; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE)
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - coins[i]]);
			}
		}
		return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1
				: dp[coins.length - 1][amount];
	}
}