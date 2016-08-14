package algo.arrays;

public class RainWaterTrappedBuilding {
	public int trap(int[] height) {
		int n = height.length;
		if (n == 0 || n == 1 || n == 2)
			return 0;
		int[] greatestLeft = new int[n];
		int[] greatestRight = new int[n];
		int max = height[0];
		for (int i = 1; i < n; i++) {
			if (height[i] >= max) {
				greatestLeft[i] = -1;
				max = height[i];
			} else {
				greatestLeft[i] = max;
			}

		}
		max = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			if (height[i] >= max) {
				greatestRight[i] = -1;
				max = height[i];
			} else {
				greatestRight[i] = max;
			}

		}
		int sum = 0;
		for (int i = 1; i < n - 1; i++) {
			if (greatestRight[i] != -1 && greatestLeft[i] != -1)
				sum += Math.min(greatestRight[i], greatestLeft[i]) - height[i];// missed
																				// subtracting
																				// height
																				// part
		}
		return sum;
	}
}
