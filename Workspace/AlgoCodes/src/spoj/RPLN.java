package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RPLN {
	static int MAXN = (int) 1e6 + 1;
	static int LOGN = 20;
	static int[][] table = new int[MAXN][LOGN];
	static int[] arr;

	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			int n, q;
			n = sc.nextInt();
			q = sc.nextInt();
			arr = sc.nextIntArray(n);
			buildSparseTable(n);
			sb.append("Scenario #" + t + ":\n");
			int l, r, k, res;
			while (q-- > 0) {
				
				l = sc.nextInt()-1;
				r = sc.nextInt()-1;
				k=(int)Math.floor(Math.log(r-l+1)/Math.log(2));
				res=Math.min(table[l][k],table[r-(1<<k)+1][k]);
				sb.append(res+"\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void buildSparseTable(int n) {
		/* table[i][j]=argmin(arr[i],....,arr[i+2^j-1]) */
		for (int i = 0; i < n; i++)
			table[i][0] = arr[i];
		for (int j = 1; (1 << j) <= n; j++) {
			for (int i = 0; (i + (1 << j)) <= n; i++) {
				table[i][j]=Math.min(table[i][j-1],table[i + (1 << (j - 1))][j - 1]);
			}
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
