//https://www.hackerearth.com/october-circuits/algorithm/shil-and-magic-of-arrays-10/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class run {
	static long mod = 1000000007;

	static class SegmentTree {

		public List<Set<Pair>> createTree(int arr[], Operation operation) {
			int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
			int size = (int) (Math.pow(2, height + 1) - 1);
			List<Set<Pair>> segmentTree = new ArrayList<Set<Pair>>(size);
			for (int i = 0; i < size; i++)
				segmentTree.add(new TreeSet<Pair>());
			constructTree(segmentTree, arr, 0, arr.length - 1, 0, operation);
			return segmentTree;
		}

		private void constructTree(List<Set<Pair>> segmentTree, int arr[],
				int low, int high, int pos, Operation combine) {
			if (low == high) {
				Pair pair = new Pair(arr[low], arr[low]);
				segmentTree.get(pos).add(pair);
				return;
			}
			int mid = (low + high) / 2;
			int left = 2 * pos + 1;
			int right = 2 * pos + 2;
			constructTree(segmentTree, arr, low, mid, left, combine);
			constructTree(segmentTree, arr, mid + 1, high, right, combine);

			Set<Pair> combined = combine.perform(segmentTree.get(left),
					segmentTree.get(right));
			segmentTree.set(pos, combined);
		}

		public Set<Pair> rangeQuery(List<Set<Pair>> segmentTree, int qlow,
				int qhigh, int len, Operation operation) {
			return rangeQuery(segmentTree, 0, len - 1, qlow, qhigh, 0,
					operation);
		}

		private Set<Pair> rangeQuery(List<Set<Pair>> segmentTree, int low,
				int high, int qlow, int qhigh, int pos, Operation operation) {
			if (qlow <= low && qhigh >= high) {
				return segmentTree.get(pos);
			}
			if (qlow > high || qhigh < low) {
				return new TreeSet<Pair>();
			}
			int mid = (low + high) / 2;
			int left = 2 * pos + 1;
			int right = 2 * pos + 2;
			return operation.perform(
					rangeQuery(segmentTree, low, mid, qlow, qhigh, left,
							operation),
					rangeQuery(segmentTree, mid + 1, high, qlow, qhigh, right,
							operation));
		}

	}

	static interface Operation {
		Set<Pair> perform(Set<Pair> set1, Set<Pair> set2);

	}

	static class Combine implements Operation {

		@Override
		public Set<Pair> perform(Set<Pair> set1, Set<Pair> set2) {
			// System.out.println("combining :"+set1+" "+set2);
			TreeSet<Pair> tmp = new TreeSet<Pair>(set1);
			tmp.addAll(set2);
			if (tmp.size() == 0)
				return tmp;
			if (tmp.size() == 1)
				return tmp;

			Pair first = tmp.pollFirst();
			int start = first.a;
			int end = first.b;

			Set<Pair> result = new TreeSet<Pair>();

			for (Pair current : tmp) {
				if (current.a <= end) {
					end = Math.max(current.b, end);
				} else {
					result.add(new Pair(start, end));
					start = current.a;
					end = current.b;
				}

			}

			result.add(new Pair(start, end));

			return result;
		}
	}

	private static void solve(FastScanner sc, PrintWriter out) {

		int n = sc.nextInt();
		int q = sc.nextInt();
		int arr[] = sc.nextIntArray(n);
		// int[] arr = { 3, 3, 7, 8, 2 };
		SegmentTree st = new SegmentTree();
		Operation combiner = new Combine();
		List<Set<Pair>> segmentTree = st.createTree(arr, combiner);
		while (q-- > 0) {

			int l = sc.nextInt() - 1;
			int r = sc.nextInt() - 1;
			out.println(st.rangeQuery(segmentTree, l, r, arr.length, combiner)
					.size());
		}

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