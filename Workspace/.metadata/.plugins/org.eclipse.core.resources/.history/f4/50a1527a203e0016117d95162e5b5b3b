package hacker.rank;

import java.util.*;

public class ConnectedCells {
	/*
3
3
1 0 1
0 1 0
1 0 1
	 * */

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[][] mat = new boolean[n][m];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				mat[i][j] = (sc.nextInt() == 1);
		int islandCount=0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				int[] islandSize = { 0 };
				if (mat[i][j]) {
					floofillSearch(mat, i, j, islandSize);
					islandCount++;
				}

				max = Math.max(max, islandSize[0]);

			}
		}
		System.out.println("Biggest island size:"+max);
		System.out.println("Number of islands:"+islandCount);

	}

	private static void floofillSearch(boolean[][] mat, int i, int j,
			int[] currSize) {
		if (i >= mat.length || i < 0 || j < 0 || j >= mat[0].length)
			return;
		if (!mat[i][j])
			return;

		currSize[0]++;
		mat[i][j] = false;
		floofillSearch(mat, i + 1, j, currSize);
		floofillSearch(mat, i - 1, j, currSize);
		floofillSearch(mat, i, j + 1, currSize);
		floofillSearch(mat, i, j - 1, currSize);
		floofillSearch(mat, i + 1, j + 1, currSize);
		floofillSearch(mat, i + 1, j - 1, currSize);
		floofillSearch(mat, i - 1, j + 1, currSize);
		floofillSearch(mat, i - 1, j - 1, currSize);
			
	}
}
