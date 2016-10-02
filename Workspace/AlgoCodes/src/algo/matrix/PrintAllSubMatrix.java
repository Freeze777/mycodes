package algo.matrix;

public class PrintAllSubMatrix {

	public static void main(String[] args) {
		// int n = 3, m = 3;
		int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printSubMatrix(mat);

	}

	private static void printSubMatrix(int[][] mat) {
		int n=mat.length;
		int m=mat[0].length;
		
		//prints all submatrix greater than or equal to 2x2
		for (int sub_n = n; sub_n >= 2; sub_n--) {
			int off_x = n - sub_n + 1;
			for (int sub_m = m; sub_m >= 2; sub_m--) {
				int off_y = m - sub_m + 1;
				for (int i = 0; i < off_x; i++) {
					for (int j = 0; j < off_y; j++) {

						for (int xx = 0; xx < sub_n; xx++) {
							for (int yy = 0; yy < sub_m; yy++) {
								System.out.print(mat[xx + i][yy + j] + " ");
							}
							System.out.print("\n");
						}
						System.out.print("\n");

					}
				}
			}
		}

	}
}
