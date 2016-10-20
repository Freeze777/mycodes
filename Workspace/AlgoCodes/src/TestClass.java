import java.io.*;
import java.math.*;
import java.util.*;

public class TestClass {
	static long mod = 1000000007;
	static Map<Integer, ArrayList<Integer>> adjList;

	private static void solve(FastScanner sc, PrintWriter out) {

		int n = sc.nextInt();
		int node_val[] = new int[n + 1];
		adjList = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 1; i <= n; i++) {
			node_val[i] = sc.nextInt();
			adjList.put(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < n - 1; i++) {
			int st = sc.nextInt();
			int d = sc.nextInt();
			adjList.get(st).add(d);
			adjList.get(d).add(st);
		}
		int opt_root = Integer.MAX_VALUE;
		long[] min = { Long.MAX_VALUE };
		for (int root = 1; root <= n; root++) {
			boolean visited[] = new boolean[n + 1];
			long f[] = new long[n + 1];
			long g[] = new long[n + 1];
			long[] sum = { 0 };
			dfs(root, visited, f, g, node_val, sum, min);
			if (sum[0] < min[0]) {
				opt_root = root;
				min[0] = sum[0];
			}
		}

		out.println(opt_root + " " + min[0]);

	}
	public static void dfs(int vertex, boolean[] visited, long[] f, long[] g,
			int[] node_val, long[] sum, long[] min) {
	
		visited[vertex] = true;
		/*if (min[0] < sum[0]) {
			//sum[0] = Long.MAX_VALUE;
			for (int i = 1; i < visited.length; i++) visited[i]=true;
			return;
		}*/
		g[vertex] += node_val[vertex];
		f[vertex] += g[vertex];
		List<Integer> adjNodes = adjList.get(vertex);
		for (Integer node : adjNodes) {
			if (!visited[node]) {
				if (min[0] >= sum[0]){
					dfs(node, visited, f, g, node_val, sum, min);
					g[vertex] += g[node];
					f[vertex] += f[node] + g[node];
				}else{
					return;
				}
			}
		}
		sum[0] += f[vertex];
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