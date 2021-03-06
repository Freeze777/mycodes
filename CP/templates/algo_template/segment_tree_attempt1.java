//https://www.hackerearth.com/october-circuits/algorithm/shil-and-magic-of-arrays-10/
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
	static class SegmentTree {

		public List<Map<Integer, Integer>> createTree(int arr[],
				Operation operation) {
			int height = (int) Math.ceil(Math.log(arr.length) / Math.log(2));
			int size = (int) (Math.pow(2, height + 1) - 1);
			List<Map<Integer, Integer>> segmentTree = new ArrayList<Map<Integer, Integer>>(
					size);
			for (int i = 0; i < size; i++)
				segmentTree.add(new HashMap<Integer, Integer>());
			constructTree(segmentTree, arr, 0, arr.length - 1, 0, operation);
			return segmentTree;
		}

		private void constructTree(List<Map<Integer, Integer>> segmentTree,
				int arr[], int low, int high, int pos, Operation combine) {
			if (low == high) {
				segmentTree.get(pos).put(arr[low], 1);
				return;
			}
			int mid = (low + high) / 2;
			int left = 2 * pos + 1;
			int right = 2 * pos + 2;
			constructTree(segmentTree, arr, low, mid, left, combine);
			constructTree(segmentTree, arr, mid + 1, high, right, combine);

			Map<Integer, Integer> combined = combine.perform(
					segmentTree.get(left), segmentTree.get(right));
			segmentTree.set(pos, combined);
		}

		public Map<Integer, Integer> rangeQuery(
				List<Map<Integer, Integer>> segmentTree, int qlow, int qhigh,
				int len, Operation operation) {
			return rangeQuery(segmentTree, 0, len - 1, qlow, qhigh, 0,
					operation);
		}

		private Map<Integer, Integer> rangeQuery(
				List<Map<Integer, Integer>> segmentTree, int low, int high,
				int qlow, int qhigh, int pos, Operation operation) {
			if (qlow <= low && qhigh >= high) {
				return segmentTree.get(pos);
			}
			if (qlow > high || qhigh < low) {
				return new HashMap<Integer, Integer>();
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

		public void updateAtPos(int arr[],
				List<Map<Integer, Integer>> segmentTree, int newVal, int index) {
			int oldVal = arr[index];
			updateVal(segmentTree, 0, arr.length - 1, newVal, oldVal, index, 0);
			arr[index] = newVal;
		}

		private void updateVal(List<Map<Integer, Integer>> segmentTree,
				int low, int high, int newVal, int oldVal, int index, int pos) {
			if (index < low || index > high) {
				return;
			}

			int value = segmentTree.get(pos).get(oldVal);
			if (value == 1)
				segmentTree.get(pos).remove(oldVal);
			else
				segmentTree.get(pos).put(oldVal, value - 1);
			int val = 0;
			if (segmentTree.get(pos).containsKey(newVal)) {
				val = segmentTree.get(pos).get(newVal);
			}
			segmentTree.get(pos).put(newVal, val + 1);
			if (low >= high) {
				return;
			}
			int mid = (low + high) / 2;
			updateVal(segmentTree, low, mid, newVal, oldVal, index, 2 * pos + 1);
			updateVal(segmentTree, mid + 1, high, newVal, oldVal, index,
					2 * pos + 2);
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

		int n = sc.nextInt();
		int q = sc.nextInt();
		int arr[] = new int[n];
		// int[] arr = { 3, 3, 7, 8, 2 };
		SegmentTree st = new SegmentTree();
		Operation combiner = new Combine();
		List<Map<Integer, Integer>> segmentTree = st.createTree(arr, combiner);
		while(q-->0){
			int x,y,z;
			x=sc.nextInt();
			if(x==1){
				y=sc.nextInt();
				z=sc.nextInt();
				st.updateAtPos(arr, segmentTree, z, y-1);				
			}else{
				y=sc.nextInt();
				Map<Integer,Integer> map=st.rangeQuery(segmentTree, 0, y-1, arr.length,combiner);
				long ans=1;
				for (Integer val:map.values()) {
					ans=(ans*pow(val+1,val+1,mod))%mod;
				}
				System.out.println(ans);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)), false);
		solve(in, out);
		//in.close();
		//out.close();
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
