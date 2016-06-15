package algo;

public class PrintMatrixDiagonally {

	public static void main(String[] args) {
		/*
		 * int A[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, {
		 * 13, 14, 15, 16 }, { 17, 18, 19, 20 } };
		 */
		int A[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		

		printDiagonalOrder_method2(A);
	}

	// O(n^2)
	public static void printDiagonalOrder_method1(int[][] A) {
		int rowLimit = A.length - 1;
		int colLimit = A[0].length - 1;
		int lineLimit = rowLimit + colLimit + 1;
		for (int line = 0; line < lineLimit; line++) {
			int startRowIndex = Math.min(line, rowLimit);
			int startColIndex = Math.max(0, line - rowLimit);

			while (startRowIndex >= 0 && startRowIndex <= rowLimit
					&& startColIndex >= 0 && startColIndex <= colLimit)
				System.out.print(A[startRowIndex--][startColIndex++] + "\t");

			System.out.println();
		}
	}

	// O(n^3)
	public static void printDiagonalOrder_method2(int[][] A) {
		int rowLimit = A.length - 1;
		int colLimit = A[0].length - 1;
		int sumLimit = rowLimit + colLimit;

		for (int sum = 0; sum <= sumLimit; sum++) {
			for (int row = rowLimit; row >= 0; row--) {
				if (row > sum)
					continue;

				for (int col = 0; col <= colLimit; col++) {
					if (col > sum)
						continue;

					if (sum == (col + row))
						System.out.print(A[row][col] + "\t");

				}

			}
			System.out.println();
		}
	}
}
