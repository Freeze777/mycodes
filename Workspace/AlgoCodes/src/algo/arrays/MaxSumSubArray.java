package algo.arrays;

public class MaxSumSubArray {
	public static void main(String[] args) {
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(maxSubArray_method2(nums));
		
	}

	/* Kadanes algo */
	public static int maxSubArray_method2(int[] nums) {
		int currSum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];
			maxSum = Math.max(maxSum, currSum);
			//currSum is no more contributing
			if (currSum < 0)
				currSum = 0;

		}
		return maxSum;
	}

	/* O(n^2) -- Prefix sum array */
	public static int maxSubArray_method1(int[] nums) {
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			nums[i] += nums[i - 1];
			max = Math.max(nums[i], max);
		}

		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				max = Math.max(nums[j] - nums[i], max);
			}
		}
		return max;
	}
}
