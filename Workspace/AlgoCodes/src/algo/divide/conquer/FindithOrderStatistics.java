package algo.divide.conquer;

public class FindithOrderStatistics {

	/**
	 * @param args
	 */

	private static int getithOrderStatistics_random(int[] A, int first,
			int last, int rank) {

		if (rank > last - first + 1 || rank <= 0) {
			return -1;
		}
		if (last > first) {
			int pivot = getPivot(A, first, last);
			int pIndex = partition(A, first, last - 1, pivot);
			if (rank == last - pIndex + 1)
				return A[pIndex];
			else if (rank < last - pIndex + 1)
				return getithOrderStatistics_random(A, pIndex + 1, last, rank);
			else
				return getithOrderStatistics_random(A, first, pIndex - 1, rank
						- (last - pIndex + 1));
		} else
			return A[first];

	}

	private static int getPivot(int[] A, int first, int last) {
		// TODO Auto-generated method stub
		int index = (((int) Math.random() * 111) % (last - first + 1)) + first;
		int temp = A[last];
		A[last] = A[index];
		A[index] = temp;
		return A[last];
	}

	private static int partition(int[] A, int first, int last, int pivot) {
		int pivotPos = last + 1;
		while (first <= last) { // Fuck....!!! i was totally fucked up for not
								// putting the equality.!Think what happens when
								// we call with 2 elements
			while (first < pivotPos && A[first] <= pivot) {
				first++;
			}
			while (last >= 0 && A[last] > pivot) {
				last--;
			}
			if (first < last) {
				int temp = A[first];
				A[first++] = A[last];
				A[last--] = temp;

			}

		}

		int temp = A[pivotPos];
		A[pivotPos] = A[first];
		A[first] = temp;

		return first;
	}

	public static void main(String[] args) {

		int[] A = { 6, 5, 7, 6, 11, 116, 3, 6, 10, 12, 6, 12 };
		for (int i = 0; i <= A.length + 1; i++) {
			System.out.println(getithOrderStatistics_random(A, 0, A.length - 1,
					i));// ith largest number
		}

		// System.out.println(A.length);
		// getithOrderStatistics_deterministic(A,0,A.length-1,3);

	}

}
