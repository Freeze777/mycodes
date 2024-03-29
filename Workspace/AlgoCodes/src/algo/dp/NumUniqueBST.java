package algo.dp;

public class NumUniqueBST {
	public static int numTrees_method1(int n) {
		if (n == 0 || n == 1)
			return 1;
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		for (int keys = 2; keys <= n; keys++) {
			for (int root = 1; root <= keys; root++)
				dp[keys] += (dp[root - 1] * dp[keys - root]);
		}
		return dp[n];
	}
	

    public static int numTrees_method2(int n, int[] dp) {
		if (dp[n] != 0)
			return dp[n];
		int sum=0;
		for (int root = 1; root <= n; root++)
			sum+= (numTrees_method2(n - root, dp) * numTrees_method2(root - 1, dp));
		dp[n]=sum;
		return dp[n];
	}

	public static int numTrees_method2(int n) {
	    if(n==0||n==1)
	        return 1;
		int[] dp = new int[n + 1];
		dp[0] = dp[1] = 1;
		return numTrees_method2(n, dp);
	}

}
