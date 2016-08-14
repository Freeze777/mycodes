package algo.matrix;

public class FloodFillSearch {
	public int numIslands(char[][] grid) {
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					floodFillSearch(grid, i, j);
				}
			}
		}
		return count;
	}

	public void floodFillSearch(char[][] grid, int i, int j) {
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length)
			return;
		if (grid[i][j] == '0')
			return;
		grid[i][j] = '0';
		floodFillSearch(grid, i - 1, j);
		floodFillSearch(grid, i, j - 1);
		floodFillSearch(grid, i + 1, j);
		floodFillSearch(grid, i, j + 1);

	}
}
