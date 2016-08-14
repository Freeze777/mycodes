package algo.dp;

public class LargestSubSquareMatrixOf1s {
	/*
	 * Given a matrix with 0s and 1s..find the largest square matrix with full
	 * 1s
	 */
	public static void main(String[] args) {
		int[][] arr = { { 0, 1, 1, 0, 0, 1 }, { 0, 0, 1, 1, 1, 0 },
				{ 1, 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0 } };
		int max = 0;
		/*
		 * 1st row and 1st column remains as it is.. i.e 0 means 0x0 matrix..1
		 * means 1x1 matrix
		 */
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				/*
				 * dp[i][j]=n means ..there is a nxn matrix ending at (i,j)
				 * ignore cells with value 0. if cell value is 1 check whether
				 * the 3 previous cells have nonzero entries take the minimum of
				 */
				if (arr[i][j] == 1)
					arr[i][j] = 1 + Math.min(arr[i - 1][j - 1],
							Math.min(arr[i - 1][j], arr[i][j - 1]));
				max = Math.max(max, arr[i][j]);
			}
		}
		System.out.println(max);
	}

}
