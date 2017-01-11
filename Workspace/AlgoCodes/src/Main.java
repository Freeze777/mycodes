import java.util.Scanner;

public class Main {
	public static int rotateMatrix_270(boolean mat[][], int N) {
		int count = 0;
		for (int x = 0; x < N / 2; x++) {
			for (int y = x; y < N - x - 1; y++) {
				boolean temp = mat[x][y];
				if (mat[x][y] != mat[y][N - 1 - x])
					count++;
				if (mat[y][N - 1 - x] != mat[N - 1 - x][N - 1 - y])
					count++;
				if (mat[N - 1 - x][N - 1 - y] != mat[N - 1 - y][x])
					count++;
				if (mat[N - 1 - y][x] != mat[x][y])
					count++;
			}
		}
		return count;
	}

	public static int rotateMatrix_180(boolean matrix[][], int N) {
		int count = 0;
		int n = N;
		for (int layer = 0; layer < N / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;
				boolean top = matrix[first][i];
				if (matrix[first][i] != matrix[last][last - offset])
					count++;
				if (matrix[last][last - offset] != top)
					count++;
				boolean leftBottom = matrix[last - offset][first];
				if (matrix[last - offset][first] != matrix[i][last])
					count++;
				if (matrix[i][last] != leftBottom)
					count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		//long start = System.currentTimeMillis();

		boolean matrix[][] = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				long li = i + 1, lj = j + 1;
				long m = (li * lj) % 7;
				m = (m * m) % 7;
				if (m == 2 || m == 4 || m == 5)
					matrix[i][j] = true;
			}
		}
		long count180 = rotateMatrix_180(matrix, n);
		long count270 = rotateMatrix_270(matrix, n);

		for (int a0 = 0; a0 < q; a0++) {
			long count;
			int angle = in.nextInt();
			angle = angle % 360;
			if (angle == 0)
				count = 0;
			else if (angle == 180)
				count = count180;
			else
				count = count270;
			System.out.println(count);
		}
		//long end = System.currentTimeMillis();
		//System.out.println((end - start) / 1000.0 + " s");
	}
}