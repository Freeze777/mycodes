package algo.matrix;
import java.util.Scanner;

/*HackerRank*/
public class MatrixRotation {
   
   	public static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
			
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	public static void rotateArray(int[] A, int len, int k) {
		reverse(A, 0, len - 1);
		reverse(A, 0, k - 1);
		reverse(A, k, len - 1);
	}

	public static void reverse(int[] A, int from, int to) {

		while (to > from) {

			int temp = A[to];
			A[to] = A[from];
			A[from] = temp;

			from++;
			to--;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		int k = sc.nextInt();
		int[][] matrix = new int[row][col];

		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				matrix[i][j] = sc.nextInt();
		int nRings = (Math.min(row, col)) / 2;
		int maxRingLen = 2 * (row + col - 2);
		int[][] rings = new int[nRings][maxRingLen];
		int[] ringLen = new int[nRings];
		for (int i = 0; i < nRings; i++) {
			ringLen[i] = 2 * ((row - 2 * i) + (col - 2 * i) - 2);
			int index = 0;
			int top = i, bottom = row - i - 1, left = i, right = col - i - 1;
			for (int j = left; j <= right; j++, index++)
				rings[i][index] = matrix[top][j];
			for (int j = top + 1; j <= bottom; j++, index++)
				rings[i][index] = matrix[j][right];
			for (int j = right - 1; j >= left; j--, index++)
				rings[i][index] = matrix[bottom][j];
			for (int j = bottom - 1; j >= top + 1; j--, index++)
				rings[i][index] = matrix[j][left];

			rotateArray(rings[i], ringLen[i], ringLen[i] - k % ringLen[i]);

			index = 0;
			for (int j = left; j <= right; j++, index++)
				matrix[top][j] = rings[i][index];
			for (int j = top + 1; j <= bottom; j++, index++)
				matrix[j][right] = rings[i][index];
			for (int j = right - 1; j >= left; j--, index++)
				matrix[bottom][j] = rings[i][index];
			for (int j = bottom - 1; j >= top + 1; j--, index++)
				matrix[j][left] = rings[i][index];

		}

		printArray(matrix);

		sc.close();
	}

}