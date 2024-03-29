package hacker.earth;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class ShootOutLondon {
	static long mod = 1000000007;

	private static void solve(FastScanner sc, PrintWriter out) {
		int max = 1000000;
		int[] isPrime = new int[max + 1];
		Arrays.fill(isPrime, 1);
		isPrime[0] = 0;
		isPrime[1] = 0;

		// consider all numbers less than sqrt(max) and mark them true
		for (int i = 2; i * i <= max; i++) {
			if (isPrime[i] == 1) {
				for (int j = i; i * j <= max; j++) {
					isPrime[i * j] = 0;
				}
			}
		}
		int n = sc.nextInt();
		TreeSet<Integer> safe = new TreeSet<Integer>();

		for (int i = 1; i <= n; i++) {
			int a = sc.nextInt();
			if (isPrime[a] == 1) {
				safe.add(i);
			}
		}
		StringBuilder ans = new StringBuilder();
		if (safe.size() == 0) {
			for (int i = 0; i < n; i++) {
				ans.append(-1 + " ");
			}
			out.print(ans);
			System.exit(0);
		}

		for (int i = 1; i <= n; i++) {
			Integer low = safe.floor(i);
			Integer high = safe.ceiling(i);
			if (low == null && high == null) {
				ans.append(-1 + " ");
			} else if (low == null) {
				ans.append(high + " ");
			} else if (high == null) {
				ans.append(low + " ");
			} else if (low.equals(high)) {
				ans.append(low + " ");
			} else {
				int t1 = Math.abs(low - i);
				int t2 = Math.abs(high - i);
				if (t1 <= t2)
					ans.append(low + " ");
				else
					ans.append(high + " ");

			}

		}
		out.print(ans);

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