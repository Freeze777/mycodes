package spoj;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DQUERY {
	static int A_MAX = (int) 1e6 + 1;
	static int n;
	static int Q_MAX = (int) 2e5 + 1;
	static int N_MAX = (int) 3e4 + 1;
	static int SQRT_N_MAX = 174;
	static ArrayList<ArrayList<Range>> buckets = new ArrayList<ArrayList<Range>>();
	static int[] count = new int[A_MAX];
	static int[] result = new int[Q_MAX];
	static int[] arr;
	static int left = 0, right = 0;
	static int distinct = 0;
	static {
		for (int bucket_id = 0; bucket_id < SQRT_N_MAX; bucket_id++) {
			buckets.add(new ArrayList<Range>());
		}
	}

	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		int n = sc.nextInt();
		int sqrtN = (int) Math.sqrt(n) + 1;
		arr = sc.nextIntArray(n);
		int q = sc.nextInt();
		int l, r;
		for (int id = 0; id < q; id++) {
			l = sc.nextInt() - 1;
			r = sc.nextInt() - 1;
			buckets.get(l / sqrtN).add(new Range(l, r, id));
		}
		for (ArrayList<Range> bucket : buckets) {
			Collections.sort(bucket);
			solve(bucket);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			sb.append(result[i] + "\n");
		}
		System.out.println(sb);

	}

	private static void solve(ArrayList<Range> bucket) {
		for (Range range : bucket) {
			// System.out.println("Processing query :" + range);
			while (left < range.l) {
				exclude(left);
				left++;
			}
			while (left > range.l) {
				include(left - 1);
				left--;
			}
			while (right <= range.r) {
				include(right);
				right++;
			}
			while (right > (range.r + 1)) {
				exclude(right - 1);
				right--;
			}

			// System.out.println(left + "," + right);
			result[range.id] = distinct;
		}

	}

	private static void include(int index) {
		count[arr[index]]++;
		if (count[arr[index]] == 1)
			distinct++;
	}

	private static void exclude(int index) {
		count[arr[index]]--;
		if (count[arr[index]] == 0)
			distinct--;
	}

	static class Range implements Comparable<Range> {
		int l, r, id;

		Range(int a, int b, int id) {
			this.l = a;
			this.r = b;
			this.id = id;
		}

		public int compareTo(Range o) {
			// TODO Auto-generated method stub
			if (this.r != o.r)
				return Integer.compare(this.r, o.r);
			else
				return Integer.compare(this.l, o.l);
			// return 0;
		}

		public boolean equals(Object o) {
			if (o instanceof Range) {
				Range p = (Range) o;
				return p.l == l && p.r == r;
			}
			return false;
		}

		public int hashCode() {
			return new Integer(l).hashCode() * 31 + new Integer(r).hashCode();
		}

		@Override
		public String toString() {
			return "Range [l=" + l + ", r=" + r + ", id=" + id + "]";
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
