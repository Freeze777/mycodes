import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class run {
	static long mod = 1000000007;

	static class FenwickTree {

		public void updateBinaryIndexedTree(
				List<Map<Integer, Integer>> binaryIndexedTree, int[] arr,
				int newVal, int index, Operation combiner) {
			int n = binaryIndexedTree.size();
			int oldVal = arr[index-1];
			arr[index-1] = newVal;
			while (index < n) {
				if (binaryIndexedTree.get(index).containsKey(oldVal)) {
					int value = binaryIndexedTree.get(index).get(oldVal);
					if (value == 1)
						binaryIndexedTree.get(index).remove(oldVal);
					else
						binaryIndexedTree.get(index).put(oldVal, value - 1);
				}
				int val = 0;
				if (binaryIndexedTree.get(index).containsKey(newVal)) {
					val = binaryIndexedTree.get(index).get(newVal);
				}
				binaryIndexedTree.get(index).put(newVal, val+1);
				index = getNext(index);
			}
			
		}

		public Map<Integer, Integer> getSum(
				List<Map<Integer, Integer>> binaryIndexedTree, int index,
				Operation combiner) {
			index = index + 1;
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			while (index > 0) {
				map = combiner.perform(map, binaryIndexedTree.get(index));
				index = getParent(index);
			}
			return map;
		}

		public List<Map<Integer, Integer>> createTree(int arr[],
				Operation combiner) {
			List<Map<Integer, Integer>> binaryIndexedTree = new ArrayList<Map<Integer, Integer>>(
					arr.length + 1);
			for (int i = 0; i <= arr.length; i++) {
				binaryIndexedTree.add(new HashMap<Integer, Integer>());
			}

			for (int i = 1; i <= arr.length; i++) {
				updateBinaryIndexedTree(binaryIndexedTree, arr, arr[i - 1], i,
						combiner);
			}
			return binaryIndexedTree;
		}

		private int getParent(int index) {
			return index - (index & -index);
		}

		private int getNext(int index) {
			return index + (index & -index);
		}

	}

	static interface Operation {
		Map<Integer, Integer> perform(Map<Integer, Integer> map1,
				Map<Integer, Integer> map2);

	}

	static class Combine implements Operation {

		@Override
		public Map<Integer, Integer> perform(Map<Integer, Integer> map1,
				Map<Integer, Integer> map2) {
			Map<Integer, Integer> retMap = new HashMap<Integer, Integer>();
			for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
				int key = entry.getKey();
				int value = entry.getValue();
				int old_val = 0;
				if (retMap.containsKey(key)) {
					old_val = retMap.get(key);
				}
				retMap.put(key, old_val + value);
			}
			for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
				int key = entry.getKey();
				int value = entry.getValue();
				int old_val = 0;
				if (retMap.containsKey(key)) {
					old_val = retMap.get(key);

				}
				retMap.put(key, old_val + value);
			}
			return retMap;
		}

	}

	private static void solve(FastScanner sc, PrintWriter out) {
		int arr[] = { 0,3, 3, 4, 5, 6, 7,7 };
		FenwickTree ft = new FenwickTree();
		Operation combiner = new Combine();
		List<Map<Integer, Integer>> binaryIndexedTree = ft.createTree(arr,
				combiner);
		Map<Integer, Integer> map = ft.getSum(binaryIndexedTree,
				arr.length-1, combiner);
		System.out.println(map);

	}

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)), false);
		solve(in, out);
		// in.close();
		// out.close();
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
