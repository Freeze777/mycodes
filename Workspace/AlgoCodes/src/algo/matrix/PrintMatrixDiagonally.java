package algo.matrix;

public class PrintMatrixDiagonally {

	public static void main(String[] args) {
		/*
		 * int A[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, {
		 * 13, 14, 15, 16 }, { 17, 18, 19, 20 } };
		 */
		int A[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };

		printDiagonalOrder_method1(A);
	}

	// O(n^2)
	public static void printDiagonalOrder_method1(int[][] A) {
		int rowLimit = A.length - 1;
		int colLimit = A[0].length - 1;
		int lineLimit = rowLimit + colLimit;
		for (int line = 0; line <=lineLimit; line++) {
			int startRowIndex = Math.min(line, rowLimit);// last row of the
															// matrix is the
															// begining of many
															// diagonals
			int startColIndex = Math.max(0, line - rowLimit);// for diagonals
																// begining in
																// the last row
																// the column
																// index start
																// from non zero
																// value

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
				// for (int row = 0; row <= rowLimit; row++) {//prints each
				// diagonal in reverse
				if (row > sum)
					continue;

				for (int col = 0; col <= colLimit; col++) {
					if (col > sum)
						break;

					if (sum == (col + row))
						System.out.print(A[row][col] + "\t");

				}

			}
			System.out.println();
		}
	}
}
