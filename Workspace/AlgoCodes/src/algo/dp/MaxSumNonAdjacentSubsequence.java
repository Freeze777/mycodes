package algo.dp;

public class MaxSumNonAdjacentSubsequence {
	public static void main(String[] args) {
		int[] arr = { 5, 5, 10, 40, 50, 35 };
		System.out.println(getMaxSum(arr));
		System.out.println(getMaxSumDP(arr));
		System.out.println(getMaxSumDPCircular(arr));
	}
	private static int getMaxSumDPCircular(int[] arr) {
		int[] dp=new int[arr.length];
		dp[0]=arr[0];
		dp[1]=Math.max(arr[0],arr[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i]=Math.max(dp[i-1],dp[i-2]+arr[i]);
		}
		return dp[arr.length-1];
	}
	
	private static int getMaxSumDP(int[] arr) {
		int[] dp=new int[arr.length];
		dp[0]=arr[0];
		dp[1]=Math.max(arr[0],arr[1]);
		for (int i = 2; i < dp.length; i++) {
			dp[i]=Math.max(dp[i-1],dp[i-2]+arr[i]);
		}
		return dp[arr.length-1];
	}

	public static int getMaxSum(int[] arr) {
		// excl and incl values available at index arr[i] gives us the max value
		// that can made excluding the element arr[i-1] and including the
		// element arr[i-1];
		// initialization for excl and incl for arr[1] i.e including and
		// excluding arr[0]
		// now how to update excl and incl???When we are at arr[i] we need to update excl and incl for arr[i+1]
		// if we have included arr[i-1] we cant include arr[i]
		// new incl=(prev excl+arr[i])
		// new excl=Math.max(prev excl,prev incl)
		int excl = 0;
		int incl = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int prev_incl=incl;
			incl=excl+arr[i];
			excl=Math.max(excl,prev_incl);
		}
		return Math.max(excl,incl);
	}
}
