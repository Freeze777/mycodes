package algo;

public class CountNumberOfOccurenceInSortedArray {
	public static void main(String[] args) {
		int A[] = { 1, 2, 2, 2, 2, 2, 5, 5, 6, 7, 7, 7, 7, 7, 7, 8, 9, 10, 10,
				10, 10 };
		int key = 2;
		int first = firstOccurence(A, key, 0, A.length - 1);
		int last = lastOccurence(A, key, first, A.length - 1);
		System.out.println(last-first+1);
	}

	private static int lastOccurence(int[] A, int key, int low, int high) {
		if (low > high)
			return -1;
		int mid = (low + high) / 2;
		if ((mid == A.length - 1 || A[mid + 1] > key) && A[mid] == key)
			return mid;
		else if (A[mid] > key)
			return lastOccurence(A, key, low, mid - 1);
		else
			return lastOccurence(A, key, mid + 1, high);

	}

	private static int firstOccurence(int[] A, int key, int low, int high) {
		if (low > high)
			return -1;
		int mid = (low + high) / 2;
		if ((mid == 0 || A[mid - 1] < key) && A[mid] == key)
			return mid;
		else if (A[mid] < key)
			return firstOccurence(A, key, mid + 1, high);
		else
			return firstOccurence(A, key, low, mid - 1);

	}
}
