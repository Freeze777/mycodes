package algo.dp;

public class UniquePaths {
	public int uniquePathsWithObstacles(int[][] arr) {
		int[][] path = new int[arr.length][arr[0].length];

		for (int i = 0; i < path.length; i++) {
			if (arr[i][0] == 1)
				break;
			path[i][0] = 1;
		}

		for (int i = 0; i < path[0].length; i++) {
			if (arr[0][i] == 1)
				break;
			path[0][i] = 1;
		}
		for (int i = 1; i < path.length; i++) {
			for (int j = 1; j < path[0].length; j++) {
				if (arr[i][j] != 1)
					path[i][j] = path[i - 1][j] + path[i][j - 1];

			}

		}

		return path[arr.length - 1][arr[0].length - 1];
	}
}