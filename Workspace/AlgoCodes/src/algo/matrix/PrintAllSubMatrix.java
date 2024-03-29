package algo.matrix;

public class PrintAllSubMatrix {

	public static void main(String[] args) {
		// int n = 3, m = 3;
		int[][] mat = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printSubMatrix(mat);

	}

	private static void printSubMatrix(int[][] mat) {
		int rows=mat.length;
		int cols=mat[0].length;
		
		//prints all submatrix greater than or equal to 2x2
		for (int subRow = rows; subRow >= 2; subRow--) {
			int rowLimit = rows - subRow + 1;
			for (int subCol = cols; subCol >= 2; subCol--) {
				int colLimit = cols - subCol + 1;
				for (int startRow = 0; startRow < rowLimit; startRow++) {
					for (int startCol = 0; startCol < colLimit; startCol++) {

						for (int i = 0; i < subRow; i++) {
							for (int j = 0; j < subCol; j++) {
								System.out.print(mat[i + startRow][j + startCol] + " ");
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
