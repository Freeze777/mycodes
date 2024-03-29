package hacker.earth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ComplexMatrixCount {
	static long mod = 1000000007;

	private static void solve(FastScanner sc, PrintWriter out) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int x = sc.nextInt();
		int[][] mat = new int[n][];
		int[][] sat = new int[n][m];
		for (int i = 0; i < n; i++) {
			mat[i] = sc.nextIntArray(m);
		}
		preProcessToSAT(mat, sat, x);
		int count = 0;
		for (int sub_n = n; sub_n >= 2; sub_n--) {
			int off_x = n - sub_n + 1;
			for (int sub_m = m; sub_m >= 2; sub_m--) {
				int off_y = m - sub_m + 1;
				for (int i = 0; i < off_x; i++) {
					for (int j = 0; j < off_y; j++) {
						if (mat[i][j] == mat[i][j + sub_m - 1]
								|| mat[i][j] == mat[i + sub_n - 1][j]
								|| mat[i + sub_n - 1][j] == mat[i + sub_n - 1][j
										+ sub_m - 1]
								|| mat[i][j + sub_m - 1] == mat[i + sub_n - 1][j
										+ sub_m - 1]
								|| mat[i][j] == mat[i + sub_n - 1][j + sub_m
										- 1]
								|| mat[i][j + sub_m - 1] == mat[i + sub_n - 1][j]) {
							if (getSubmatrixSum(sat, i, j, i + sub_n - 1, j
									+ sub_m - 1) >= k) {
								/*
								 * for (int xx = 0; xx < sub_n; xx++) { for (int
								 * yy = 0; yy < sub_m; yy++) {
								 * System.out.print(mat[xx + i][yy + j]+" "); }
								 * System.out.print("\n"); }
								 * System.out.print("\n");
								 */
								count++;

							}

						}
					}
				}
			}
		}
		out.print(count);
	}

	private static int getSubmatrixSum(int[][] sat, int x1, int y1, int x2,
			int y2) {
		int sum = sat[x2][y2];
		if (x1 >= 1)
			sum -= sat[x1 - 1][y2];
		if (y1 >= 1)
			sum -= sat[x2][y1 - 1];
		if (x1 >= 1 && y1 >= 1)
			sum += sat[x1 - 1][y1 - 1];
		return sum;

	}

	private static void preProcessToSAT(int[][] mat, int[][] sat, int x) {
		for (int i = 0; i < sat.length; i++) {
			for (int j = 0; j < sat[0].length; j++) {
				sat[i][j] = (mat[i][j] == x) ? 1 : 0;

				if (i >= 1)
					sat[i][j] += sat[i - 1][j];
				if (j >= 1)
					sat[i][j] += sat[i][j - 1];
				if (i >= 1 && j >= 1)
					sat[i][j] -= sat[i - 1][j - 1];
			}

		}

	}

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)), false);
		solve(in, out);
		in.close();
		out.close();
	}

	public static long gcd(long x, long y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	public static long pow(long n, long p, long m) {
		long result = 1;
		if (p == 0)
			return 1;
		if (p == 1)
			return n;
		while (p != 0) {
			if (p % 2 == 1)
				result *= n;
			if (result >= m)
				result %= m;
			p >>= 1;
			n *= n;
			if (n >= m)
				n %= m;
		}
		return result;
	}

	static class Pair implements Comparable<Pair> {
		int a, b;

		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}

		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.a != o.a)
				return Integer.compare(this.a, o.a);
			else
				return Integer.compare(this.b, o.b);
			// return 0;
		}

		public boolean equals(Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return p.a == a && p.b == b;
			}
			return false;
		}

		public int hashCode() {
			return new Integer(a).hashCode() * 31 + new Integer(b).hashCode();
		}
	}

	static class FastScanner {
		BufferedReader reader;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream));
			st = null;
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = reader.readLine();
					if (line == null) {
						return null;
					}
					st = new StringTokenizer(line);
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}
			return st.nextToken();
		}

		String nextLine() {
			String s = null;
			try {
				s = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		char nextChar() {
			return next().charAt(0);
		}

		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			int i = 0;
			while (i < n) {
				arr[i++] = nextInt();
			}
			return arr;
		}

		long[] nextLongArray(int n) {
			long[] arr = new long[n];
			int i = 0;
			while (i < n) {
				arr[i++] = nextLong();
			}
			return arr;
		}

		int[] nextIntArrayOneBased(int n) {
			int[] arr = new int[n + 1];
			int i = 1;
			while (i <= n) {
				arr[i++] = nextInt();
			}
			return arr;
		}

		long[] nextLongArrayOneBased(int n) {
			long[] arr = new long[n + 1];
			int i = 1;
			while (i <= n) {
				arr[i++] = nextLong();
			}
			return arr;
		}

		void close() {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}