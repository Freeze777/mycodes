package algo.matrix;

/*https://leetcode.com/problems/surrounded-regions/*/
public class CaptureRegions {
	public void floodFillSearch(char[][] board, int i, int j) {
		if (i < 0 || j < 0 || j >= board[0].length || i >= board.length)
			return;
		if (board[i][j] == 'X' || board[i][j] == 'F')
			return;
		board[i][j] = 'F';
		floodFillSearch(board, i + 1, j);
		floodFillSearch(board, i, j + 1);
		floodFillSearch(board, i - 1, j);
		floodFillSearch(board, i, j - 1);
	}

	public void solve(char[][] board) {
		if (board.length == 0)
			return;
		if (board[0].length == 0)
			return;
		for (int i = 0; i < board[0].length; i++) {
			if (board[0][i] == 'O')
				floodFillSearch(board, 0, i);
			if (board[board.length - 1][i] == 'O')
				floodFillSearch(board, board.length - 1, i);
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == 'O')
				floodFillSearch(board, i, 0);
			if (board[i][board[0].length - 1] == 'O')
				floodFillSearch(board, i, board[0].length - 1);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == 'F')
					board[i][j] = 'O';
			}
		}

	}
}
