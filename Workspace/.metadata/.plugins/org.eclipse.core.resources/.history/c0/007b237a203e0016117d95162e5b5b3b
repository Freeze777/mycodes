package hacker.rank;

public class SmallestMissingElement_LogN {

	public static void main(String[] args) {
		int[] array = { 0, 1, 2, 4, 5 };
		System.out.println(search(array, 0, array.length - 1));

	}

	private static int search(int[] array, int first, int last) {
		int mid = (last + first) / 2;
		if (last >= first) {
			if (array[mid] > mid)
				return search(array, first, mid - 1);
			else
				return search(array, mid + 1, last);

		} else {
			int result = ((array[mid] == mid) ? first : last);
			return (result == -1) ? 0 : result;
		}

	}

}
