package hacker.rank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class LCACompleteBT_Delivery {
	static long mod = 1000000007;

	public static int dist(int a, int b) {
		if (a == b)
			return 0;
		// System.out.print("dist  "+ loc1+"   "+loc2);
		int level1 = (int) Math.floor(Math.log(a) / Math.log(2)) + 1;
		int level2 = (int) Math.floor(Math.log(b) / Math.log(2)) + 1;
		int distance = 0;
		int diff = Math.abs(level1 - level2);
		distance = 0;
		int lcaLevel = 0;
		if (level1 < level2) {
			b = b >> diff;
			lcaLevel += diff;
		} else {
			a = a >> diff;
			lcaLevel += diff;
		}
		for (; a != b;) {
			a = a >> 1;
			b = b >> 1;
			distance += 2;
		}
		distance += lcaLevel;
		return distance;
	}

	private static void solve(FastScanner sc, PrintWriter out) {
		int n, m, q;
		n = sc.nextInt();
		m = sc.nextInt();
		q = sc.nextInt();
		Map<Integer, Set<Integer>> foodTypeShopsList = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < m; i++) {
			int quantity = sc.nextInt();
			foodTypeShopsList.put(i, new HashSet<Integer>());
			for (int j = 0; j < quantity; j++)
				foodTypeShopsList.get(i).add(sc.nextInt());
		}
		int currentLoc = 1;
		long ans = 0;
		for (int i = 0; i < q; i++) {
			int food = sc.nextInt() - 1;
			int orderLoc = sc.nextInt();
			Set<Integer> stores = foodTypeShopsList.get(food);
			int min = Integer.MAX_VALUE;
			for (int store : stores) {
				if (store == currentLoc || store == orderLoc) {
					min = dist(currentLoc, orderLoc);
					break;
				} else {
					min = Math.min(
							dist(currentLoc, store) + dist(store, orderLoc),
							min);
				}
			}
			ans += min;
			currentLoc = orderLoc;
		}
		out.println(ans);
	}

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)), false);
		solve(in, out);
		// in.close();
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

		@Override
		public String toString() {
			return "Pair [a=" + a + ", b=" + b + "]";
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
