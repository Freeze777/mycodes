package algo.arrays;

public class MoveZeroes {
	public void moveZeroes(int[] nums) {
		if (nums.length == 0)
			return;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0)
				count++;
		}
		int k = 0;
		for (int i = 0; i < count; i++) {
			while (nums[k] == 0)
				k++;
			nums[i] = nums[k];
			k++;
		}
		k = nums.length - 1;
		count = nums.length - count;
		while (count-- > 0) {
			nums[k] = 0;
			k--;
		}

	}
}
