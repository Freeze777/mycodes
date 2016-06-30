package algo;

/**
 * https://en.wikipedia.org/wiki/Summed_area_table
 **/
public class EfficientSubmatrixSumComputation {
	public static void main(String[] args) {
		int row = 3;
		int col = 4;
		int[][] mat = new int[row][col];

		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				mat[i][j] = (int) (Math.random() * 101);

		Utils.printArray(mat);

		int[][] sat = new int[row][col];
		preProcessToSAT(mat, sat);

		System.out.println();

		Utils.printArray(sat);

		int x1 = 0, y1 = 1, x2 = 2, y2 = 2;
		int subMatSum=getSubmatrixSum(sat, x1, y1,x2, y2);
		System.out.println(subMatSum);

	}

	private static int getSubmatrixSum(int[][] sat, int x1, int y1, int x2,
			int y2) {
		int sum = sat[x2][y2];
		if (x1 >= 1)
			sum -= sat[x1 - 1][y2];
		if (y1 >= 1)
			sum -= sat[x2][y1-1];
		if (x1 >= 1 && y1 >= 1)
			sum += sat[x1 - 1][y1 - 1];
		return sum;

	}

	private static void preProcessToSAT(int[][] mat, int[][] sat) {
		for (int i = 0; i < sat.length; i++) {
			for (int j = 0; j < sat[0].length; j++) {
				sat[i][j] = mat[i][j];

				if (i >= 1)
					sat[i][j] += sat[i - 1][j];
				if (j >= 1)
					sat[i][j] += sat[i][j - 1];
				if (i >= 1 && j >= 1)
					sat[i][j] -= sat[i - 1][j - 1];
			}

		}

	}

}
