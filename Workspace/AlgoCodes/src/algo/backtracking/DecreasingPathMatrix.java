package algo.backtracking;

public class DecreasingPathMatrix {
	static int[] dirx = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static int[] diry = { 1, -1, 0, 0, 1, 1, -1, -1 };

	public static void main(String[] args) {
		int[][] arr = { { 100, 120, 85, 90, 20 }, { 80, 90, 110, 80, 70 },
				{ 75, 100, 85, 100, 75 }, { 90, 70, 120, 80, 70 },
				{ 100, 60, 65, 60, 50 } };
		System.out.println(countPaths(arr, 0, 0, 4, 4));
	}

	private static int countPaths(int[][] arr, int si, int sj, int ti, int tj) {
		int[] count = { 0 };
		dfs(arr, si, sj, ti, tj, count, arr[si][sj] + " ");
		return count[0];
	}

	private static void dfs(int[][] arr, int si, int sj, int ti, int tj,
			int[] count, String s) {
		// System.out.println("here");
		if (si < 0 || sj < 0 || si >= arr.length || sj >= arr[0].length)
			return;
		if (si == ti && sj == tj) {
			System.out.println(s);
			count[0]++;
			return;
		}
		for (int d = 0; d < dirx.length; d++) {
			int nxti = si + dirx[d];
			int nxtj = sj + diry[d];
			if (nxti < 0 || nxtj < 0 || nxti >= arr.length
					|| nxtj >= arr[0].length)
				continue;
			if (arr[si][sj] >= arr[nxti][nxtj])
				dfs(arr, nxti, nxtj, ti, tj, count, s + arr[nxti][nxtj] + " ");
		}

	}
}
