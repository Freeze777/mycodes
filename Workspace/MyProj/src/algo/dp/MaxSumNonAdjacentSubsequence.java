package algo.dp;

public class MaxSumNonAdjacentSubsequence {
	public static void main(String[] args) {
		int[] arr = { 5, 5, 10, 40, 50, 35 };
		System.out.println(getMaxSum(arr));
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
