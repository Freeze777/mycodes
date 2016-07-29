package algo.backtracking;

import java.util.ArrayList;
import java.util.List;

class pair {
	int x;
	int y;

	public pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "pair [x=" + x + ", y=" + y + "]";
	}

}

public class WordMatchMatrix {

	int[] x = { 1, -1, 0, 0 };
	int[] y = { 0, 0, 1, -1 };

	public List<pair> getStart(char[][] board, char ch) {
		List<pair> l = new ArrayList<pair>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (ch == board[i][j])
					l.add(new pair(i, j));
			}
		}
		return l;
	}

	public void exist(char[][] board, int i, int j, String word, int w,
			boolean[] found) {
		if (w == word.length()) {
			found[0] = found[0] || true;
			return;
		}
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)
			return;
		for (int d = 0; d < 4; d++) {
			int new_x = i + x[d];
			int new_y = j + y[d];
			if (new_x < 0 || new_y < 0 || new_x >= board.length
					|| new_y >= board[0].length)
				continue;
			else if (board[new_x][new_y] == word.charAt(w)) {
				board[new_x][new_y] = '-';
				exist(board, new_x, new_y, word, w + 1, found);
				board[new_x][new_y] = word.charAt(w);
			}
		}
	}

	public boolean exist(char[][] board, String word) {
		if (board.length == 0 || board[0].length == 0)
			return false;
		boolean[] found = { false };
		List<pair> l = getStart(board, word.charAt(0));
		for (pair p : l) {
			board[p.x][p.y] = '-';
			exist(board, p.x, p.y, word, 1, found);
			board[p.x][p.y] = word.charAt(0);
			if (found[0])
				break;
		}

		return found[0];
	}

}
