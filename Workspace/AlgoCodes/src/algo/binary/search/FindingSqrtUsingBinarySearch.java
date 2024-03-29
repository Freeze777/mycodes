package algo.binary.search;

public class FindingSqrtUsingBinarySearch {

	public static void main(String[] args) {
		int key = Integer.MAX_VALUE;
		int high = key;
		int low = 0;
		System.out.println(sqrtBinSearch((long) key, (long) low, (long) high));
		new String().hashCode();
	}

	/* Had to change int --> long to avoid overflow errors */
	private static long sqrtBinSearch(long key, long low, long high) {
		long mid = (low + high) / 2;
		// int sqr = mid * mid; // bug here.. overflow issue..!!!when key
		// =Integer.MAX_VALUE-->stackoverflow
		long sqr = mid * mid;
		if (low > high)
			return mid;
		if (sqr > key)
			return sqrtBinSearch(key, low, mid - 1);
		else if (sqr < key)
			return sqrtBinSearch(key, mid + 1, high);
		else
			return mid;

	}
}
