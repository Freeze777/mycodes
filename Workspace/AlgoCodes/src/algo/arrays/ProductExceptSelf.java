package algo.arrays;

public class ProductExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		int[] output = new int[nums.length];
		left[0] = 1;
		right[nums.length - 1] = 1;
		for (int i = 1, j = nums.length - 2; i < nums.length && j >= 0; j--, i++) {
			left[i] = left[i - 1] * nums[i - 1];
			right[j] = right[j + 1] * nums[j + 1];
		}
		for (int i = 0; i < nums.length; i++) {
			output[i] = left[i] * right[i];
		}
		return output;
	}
}
