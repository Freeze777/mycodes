package algo;

import java.util.Arrays;
import java.util.HashMap;

public class SearchInRotatedArray {
	public static void main(String[] args) {
		int A[] = { 20, 24, 27, 30, -3, 2, 7, 9, 10, 12, 15, 19 };
		int key = 30;
		// int A[]={2,7,9,10,12,15,19,20};
		int brkpt = findBreakPoint(A, 0, A.length - 1);
		int min = findMin(A);
		int index = modifiedBinarySearch(A, brkpt, key);
		System.out.println(index);
	}

	private static int modifiedBinarySearch(int[] A, int brkpt, int key) {
		if (key >= A[brkpt + 1] && key <= A[A.length - 1])
			return Arrays.binarySearch(A, brkpt + 1, A.length, key);
		else
			return Arrays.binarySearch(A, 0, brkpt + 1, key);
	}

	public static int findMin(int[] A) {
		return A[findBreakPoint(A, 0, A.length - 1) + 1];
	}

	public static int findBreakPoint(int[] A, int low, int high) {
		if (low >= high) // array is not rotated
			return -1;

		int mid = (low + high) / 2;

		if (A[mid] > A[mid + 1])// check for break point
			return mid;
		if (A[mid - 1] > A[mid])// check for break point
			return mid - 1;
		if (A[mid] < A[high]) // mid to high subarray is sorted,so break point
								// lies in the other half
			return findBreakPoint(A, low, mid - 1);
		return findBreakPoint(A, mid + 1, high); // low to mid subarray is
													// sorted,so check in the
													// other half

	}
}
