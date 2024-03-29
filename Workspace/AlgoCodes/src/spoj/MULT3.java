package spoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MULT3 {
	static long mod = 1000000007;

	static class Node {
		int count3;

		public Node() {
			this.count3 = 0;
		}

		public Node(int count3) {
			this.count3 = count3;
		}

	}

	static class SegmentTree {

		public Node[] createTree(int arr[], Operation operation) {
			int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
			int size = (int) (Math.pow(2, height + 1) - 1);
			Node[] segmentTree = new Node[size];
			constructTree(segmentTree, arr, 0, arr.length - 1, 0, operation);
			return segmentTree;
		}

		private void constructTree(Node[] segmentTree, int arr[], int low,
				int high, int pos, Operation combine) {
			if (low == high) {
				segmentTree[pos] = new Node((arr[low] % 3 == 0) ? 1 : 0);
				return;
			}
			int mid = (low + high) >>1;
			int left = (pos<<1) | 1;
			int right = (pos<<1) + 2;
			constructTree(segmentTree, arr, low, mid, left, combine);
			constructTree(segmentTree, arr, mid + 1, high, right, combine);

			Node combined = combine.perform(segmentTree[left],
					segmentTree[right]);
			segmentTree[pos] = combined;
		}

		public Node rangeQuery(Node[] segmentTree, int qlow, int qhigh,
				int len, Operation operation) {
			return rangeQuery(segmentTree, 0, len - 1, qlow, qhigh, 0,
					operation);
		}

		private Node rangeQuery(Node[] segmentTree, int low, int high,
				int qlow, int qhigh, int pos, Operation operation) {
			if (qlow <= low && qhigh >= high) {
				return segmentTree[pos];
			}
			if (qlow > high || qhigh < low) {
				return new Node();
			}
			int mid = (low + high) >>1;
			int left = (pos<<1) | 1;
			int right = (pos<<1) + 2;
			return operation.perform(
					rangeQuery(segmentTree, low, mid, qlow, qhigh, left,
							operation),
							rangeQuery(segmentTree, mid + 1, high, qlow, qhigh, right,
									operation));
		}

		public void updateAtPos(int arr[], Node[] segmentTree, int inc,
				int index) {
			int oldVal = arr[index];
			updateVal(segmentTree, 0, arr.length - 1, oldVal + inc, oldVal,
					index, 0);
			arr[index] = oldVal + inc;
		}

		private void updateVal(Node[] segmentTree, int low, int high,
				int newVal, int oldVal, int index, int pos) {
			if (index < low || index > high) {
				return;
			}

			int oldCount = segmentTree[pos].count3;
			oldCount -= ((oldVal % 3 == 0) ? 1 : 0);
			int newCount = oldCount + ((newVal % 3 == 0) ? 1 : 0);
			segmentTree[pos].count3 = newCount;
			
			if (low >= high) {
				return;
			}
			int mid = (low + high) >>1;
			updateVal(segmentTree, low, mid, newVal, oldVal, index, (pos<<1) | 1);
			updateVal(segmentTree, mid + 1, high, newVal, oldVal, index,
					(pos<<1) + 2);
		}
	}

	static interface Operation {
		Node perform(Node n1, Node n2);

	}

	static class Combine implements Operation {

		@Override
		public Node perform(Node n1, Node n2) {
			Node combined = new Node(n1.count3 + n2.count3);
			return combined;
		}
	}

	private static void solve(FastScanner sc, PrintWriter out) {

		int n = sc.nextInt();
		int q = sc.nextInt();
		int arr[] = new int[n];
		SegmentTree st = new SegmentTree();
		Operation combiner = new Combine();
		Node[] segmentTree = st.createTree(arr, combiner);
		while (q-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int z = sc.nextInt();
			if (x == 0) {
				for (int i = y; i <= z; i++) {
					st.updateAtPos(arr, segmentTree, 1, i);
				}

			} else {
				Node node = st.rangeQuery(segmentTree, y, z, arr.length,
						combiner);
				out.println(node.count3);

			}
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
