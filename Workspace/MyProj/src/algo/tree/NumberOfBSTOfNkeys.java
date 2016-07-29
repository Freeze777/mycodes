package algo.tree;

public class NumberOfBSTOfNkeys {
	// bottom-up
	public static int countBST_method1(int n) {
		if (n == 0 || n == 1)
			return 1;
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		// bottom-up:
		/*
		 * we assume we have i keys with us then vary i from 2 to n. i.e
		 * building up towards the solution. Suppose we have i keys, then we can
		 * choose any of them(say j) to be the root. Then we have j-1 keys in
		 * the left and (i-j) keys in the right. These will be already computed.
		 */
		for (int i = 2; i < dp.length; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] += (dp[i - j] * dp[j - 1]);
			}
		}
		return dp[n];
	}

	// top-down recursion
	/*
	 * More intuitive method with memoization. keys 1,2,3......n. If we choose
	 * root=i then left subtree is made of 1,2,..i-1 keys -->(i-1 keys) and
	 * right subtree is made of i+1,i+2,..n keys--->(n-i keys)
	 */
	public static int countBST_method2(int n, int[] dp) {
		if (dp[n] != 0)
			return dp[n];
		for (int root = 1; root <= n; root++)
			dp[n] += (countBST_method2(root - 1, dp) * countBST_method2(n
					- root, dp));
		return dp[n];
	}

	public static int countBST_method2(int n) {
		if (n == 0 || n == 1)
			return 1;
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		return countBST_method2(n, dp);
	}

	public static void main(String[] args) {
		System.out.println(countBST_method2(5));
	}
}
