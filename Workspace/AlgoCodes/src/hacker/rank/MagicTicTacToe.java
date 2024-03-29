package hacker.rank;
import java.util.Scanner;
import java.util.TreeSet;
/*https://www.hackerrank.com/contests/ncr-codesprint/challenges/mega-tic-tac-toe*/
public class MagicTicTacToe {
	static class Cell {
		char val;
		int ho = 0, hx = 0;
		int vo = 0, vx = 0;
		int ldo = 0, ldx = 0;
		int rdo = 0, rdx = 0;

		public Cell(char val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return "Cell [val=" + val + ", ho=" + ho + ", hx=" + hx + ", vo="
					+ vo + ", vx=" + vx + ", ldo=" + ldo + ", ldx=" + ldx
					+ ", rdo=" + rdo + ", rdx=" + rdx + "]";
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int g = sc.nextInt();
		while (g-- > 0) {
			int n, m, k;
			n = sc.nextInt();
			m = sc.nextInt();
			k = sc.nextInt();
			Cell[][] arr = new Cell[n][m];
			for (int i = 0; i < arr.length; i++) {
				String row = sc.next();
				for (int j = 0; j < row.length(); j++) {
					arr[i][j] = new Cell(row.charAt(j));
				}
			}
			System.out.println(compute(arr, k, n, m));
		}
	}

	private static String compute(Cell[][] arr, int k, int n, int m) {

		TreeSet<Character> winner = new TreeSet<Character>();
		if (arr[0][0].val == 'O') {
			arr[0][0].ho = 1;
			arr[0][0].vo = 1;
			arr[0][0].ldo = 1;
			arr[0][0].rdo = 1;
			if (k == 1)
				winner.add('O');
		} else if (arr[0][0].val == 'X') {
			arr[0][0].hx = 1;
			arr[0][0].vx = 1;
			arr[0][0].ldx = 1;
			arr[0][0].rdx = 1;
			if (k == 1)
				winner.add('X');
		}

		for (int j = 1; j < m; j++) {

			if (arr[0][j].val == 'O') {
				arr[0][j].vo = 1;
				arr[0][j].ldo = 1;
				arr[0][j].rdo = 1;
				arr[0][j].ho = 1;
				if (arr[0][j - 1].val == 'O') {
					arr[0][j].ho += arr[0][j - 1].ho;
				}
				if (arr[0][j].ho >= k)
					winner.add('O');

			} else if (arr[0][j].val == 'X') {
				arr[0][j].vx = 1;
				arr[0][j].ldx = 1;
				arr[0][j].rdx = 1;
				arr[0][j].hx = 1;
				if (arr[0][j - 1].val == 'X') {
					arr[0][j].hx += arr[0][j - 1].hx;
				}

				if (arr[0][j].hx >= k)
					winner.add('X');
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j].val == 'O') {
					arr[i][j].ho = 1;
					arr[i][j].vo = 1;
					arr[i][j].ldo = 1;
					arr[i][j].rdo = 1;
					// vertical
					if (arr[i - 1][j].val == 'O') {
						arr[i][j].vo += arr[i - 1][j].vo;
					}
					// horizontal
					if ((j >= 1) && arr[i][j - 1].val == 'O') {
						arr[i][j].ho += arr[i][j - 1].ho;
					}
					// right diagonal
					if ((j <= m - 2) && arr[i - 1][j + 1].val == 'O') {
						arr[i][j].rdo += arr[i - 1][j + 1].rdo;
					}
					// left diagonal
					if ((j >= 1) && arr[i - 1][j - 1].val == 'O') {
						arr[i][j].ldo += arr[i - 1][j - 1].ldo;
					}
					if (arr[i][j].ho >= k || arr[i][j].vo >= k
							|| arr[i][j].ldo >= k || arr[i][j].rdo >= k)
						winner.add('O');

				} else if (arr[i][j].val == 'X') {
					arr[i][j].hx = 1;
					arr[i][j].vx = 1;
					arr[i][j].ldx = 1;
					arr[i][j].rdx = 1;
					// vertical
					if (arr[i - 1][j].val == 'X') {
						arr[i][j].vx += arr[i - 1][j].vx;
					}
					// horizontal
					if ((j >= 1) && arr[i][j - 1].val == 'X') {
						arr[i][j].hx += arr[i][j - 1].hx;
					}
					// right diagonal
					if ((j <= m - 2) && arr[i - 1][j + 1].val == 'X') {
						arr[i][j].rdx += arr[i - 1][j + 1].rdx;
					}
					// left diagonal
					if ((j >= 1) && arr[i - 1][j - 1].val == 'X') {
						arr[i][j].ldx += arr[i - 1][j - 1].ldx;
					}

					if (arr[i][j].hx >= k || arr[i][j].vx >= k
							|| arr[i][j].ldx >= k || arr[i][j].rdx >= k)
						winner.add('X');
				}
			}

		}
		/*for (int x = 0; x < arr.length; x++) {
			System.out.println("ROW:" + x);
			for (int y = 0; y < arr.length; y++) {
				System.out.println("COL:" + y + " " + arr[x][y]);
			}
			System.out.println();
		}*/
		if (winner.size() == 2 || winner.isEmpty())
			return "NONE";
		char w = winner.pollFirst();
		return w == 'O' ? "WIN" : "LOSE";
	}
}
