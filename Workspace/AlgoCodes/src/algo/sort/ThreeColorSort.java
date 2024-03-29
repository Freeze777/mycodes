package algo.sort;

import algo.Utils;

public class ThreeColorSort {
	public static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/*
	 * Array containing 3 distinct elements ..sort it in O(n) without using
	 * count sort
	 */
	public static void main(String[] args) {
		int[] arr = { 10, 30, 20, 10, 10, 20, 30, 10, 20, 30, 20, 10, 20, 30,
				10, 10, 20 };
		int low = 0, mid = 0, high = arr.length - 1;
		/*
		 * Invariant: arr[0..low-1] are all 10 , arr[low..mid-1]=20
		 * a[high+1..n-1] =30; a[mid..high] are unknown
		 */
		while (mid <= high) {
			/* worry about the arr[mid] and the invariant..!!! */
			switch (arr[mid]) {
			case 10:
				swap(arr, mid, low);
				low++;
				mid++;//idk why we need this..!!!
				break;
			case 20:
				mid++;
				break;
			case 30:
				swap(arr, mid, high);
				high--;
				break;
			}
		}
		Utils.printArray(arr);
	}

}
