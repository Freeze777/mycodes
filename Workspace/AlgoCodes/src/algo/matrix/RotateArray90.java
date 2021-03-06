package algo.matrix;

import algo.Utils;

public class RotateArray90 {
	public static void main(String[] args) {
		int row = 4;
		int col = 4;
		int[][] mat = new int[row][col];

		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				mat[i][j] = (int) (Math.random() * 101);

		Utils.printArray(mat);
		System.out.println();
		rotateMatrix90Right(mat);
		rotateMatrix90Right(mat);
		rotateMatrix90Right(mat);
		rotateMatrix90Right(mat);

		Utils.printArray(mat);
	}

	private static void rotateMatrix90Right(int[][] mat) {

		transposeMatrix(mat);

		for (int k = 0; k < mat.length; k++) {
			for (int i = 0, j = mat[0].length - 1; i < j; i++, j--) {
				int temp = mat[k][i];
				mat[k][i] = mat[k][j];
				mat[k][j] = temp;

			}
		}
	}

	private static void transposeMatrix(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < i; j++) {
				int temp = mat[i][j];
				mat[i][j] = mat[j][i];
				mat[j][i] = temp;
			}

		}
	}
}
