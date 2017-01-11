package algo.dp;

public class BalancedPartitioning {
	public static void main(String[] args) {
		int A[] = { 4, 5, 3, 3, 3 };
		int sum = 0;

		for (int i = 0; i < A.length; i++)
			sum += A[i];

		int k = sum / 2;

		boolean dp[][] = new boolean[k + 1][A.length + 1];

		for (int j = 0; j < A.length + 1; j++)
			dp[0][j] = true;

		for (int partition_sum = 1; partition_sum < k + 1; partition_sum++) {
			for (int j = 1; j < A.length + 1; j++) {
				dp[partition_sum][j] = dp[partition_sum][j - 1];
				if (partition_sum >= A[j - 1])
					dp[partition_sum][j] = dp[partition_sum][j] || dp[partition_sum - A[j - 1]][j - 1];
			}
		}

		while (k >= 0 && !dp[k--][A.length]);
		
		System.out.println("Partitions:" + (k + 1) + "," + (sum - (k + 1)));

	}
}
