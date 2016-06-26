package hacker.rank;

import java.util.Scanner;

public class MaxEscapesFromEnemyGrid {
	/* *
	 * 1 for prisoner 0 for enemy prisoner escapes if its at the edge of the
	 * grid
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[][] mat = new boolean[n][m];
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				mat[i][j] = (sc.nextInt() == 1);

		int prisoners = 0;

		for (int i = 0; i < mat.length; i++) {
			int islandSize[] = { 0 };
			if (mat[i][0]) {
				floodfillSearch(mat, i, 0, islandSize);
				prisoners += islandSize[0];
			}

			if (mat[i][mat[0].length - 1]) {
				islandSize[0] = 0;
				floodfillSearch(mat, 0, mat[0].length - 1, islandSize);
				prisoners += islandSize[0];
			}

		}
		for (int j = 0; j < mat[0].length; j++) {
			int islandSize[] = { 0 };
			if (mat[0][j]) {
				islandSize[0] = 0;
				floodfillSearch(mat, 0, j, islandSize);
				prisoners += islandSize[0];
			}
			if (mat[mat.length - 1][j]) {
				islandSize[0] = 0;
				floodfillSearch(mat, mat.length - 1, j, islandSize);
				prisoners += islandSize[0];
			}
		}
		System.out.println(prisoners);
	}

	private static void floodfillSearch(boolean[][] mat, int i, int j,
			int[] islandSize) {
		if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length)
			return;
		if (!mat[i][j])
			return;

		islandSize[0]++;
		mat[i][j] = false;

		floodfillSearch(mat, i + 1, j, islandSize);
		floodfillSearch(mat, i - 1, j, islandSize);
		floodfillSearch(mat, i, j + 1, islandSize);
		floodfillSearch(mat, i, j - 1, islandSize);
		floodfillSearch(mat, i + 1, j + 1, islandSize);
		floodfillSearch(mat, i + 1, j - 1, islandSize);
		floodfillSearch(mat, i - 1, j + 1, islandSize);
		floodfillSearch(mat, i - 1, j - 1, islandSize);

	}

}