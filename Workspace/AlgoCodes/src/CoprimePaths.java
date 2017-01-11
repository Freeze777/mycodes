import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class CoprimePaths {
	static Map<Integer, List<Integer>> adjList;
	static int[] nodeValues;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = n - 1;
		int q = sc.nextInt();
		int query[][] = new int[q][2];
		Set<Integer> left = new HashSet<Integer>();
		Set<Integer> right = new HashSet<Integer>();
		nodeValues = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			nodeValues[i] = sc.nextInt();
		}
		adjList = getUnweightedUndirectedGraph(sc, m);

		// System.out.println(bfsTrees);
		for (int i = 0; i < q; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			left.add(u);
			right.add(v);
			query[i][0] = u;
			query[i][1] = v;
		}
		Map<Integer, Map<Integer, Integer>> bfsTrees = new HashMap<Integer, Map<Integer, Integer>>();
		boolean leftflag = false;
		if (left.size() < right.size()) {
			for (int src : left) {
				bfsTrees.put(src, breadthFirstSearch(src));
			}
			leftflag = true;
		} else {
			for (int src : right) {
				bfsTrees.put(src, breadthFirstSearch(src));
			}
		}
		for (int i = 0; i < query.length; i++) {
			int u, v;
			if (leftflag) {
				u = query[i][0];
				v = query[i][1];
			} else {
				v = query[i][0];
				u = query[i][1];
			}

			System.out.println(solve(u, v, bfsTrees.get(u)));
		}

	}

	private static long solve(Integer u, Integer v,
			Map<Integer, Integer> ubfsTree) {
		long count = 0;
		List<Integer> path = new ArrayList<Integer>();
		Integer node = v;
		while (node != null) {
			path.add(node);
			node = ubfsTree.get(node);
		}
		// System.out.println(path);
		int size = path.size();
		for (int i = 0; i < size; i++) {
			int x = nodeValues[path.get(i)];
			if (MRIsPrime(x)) {
				count += (size - i - 1);
				continue;
			}
			for (int j = i + 1; j < size; j++) {
				int y = nodeValues[path.get(j)];

				if (gcd(x, y) == 1)
					count++;
			}

		}

		return count;
	}

	public static Map<Integer, Integer> breadthFirstSearch(int s) {

		Set<Integer> discovered = new HashSet<Integer>();
		Queue<Integer> bfsQ = new LinkedList<Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		bfsQ.add(s);
		bfsQ.add(null);
		parent.put(s, null);
		discovered.add(s);

		while (!bfsQ.isEmpty()) {
			Integer current = bfsQ.remove();

			if (current != null) {
				List<Integer> currList = adjList.get(current);
				for (Integer adjVertex : currList) {
					if (!discovered.contains(adjVertex)) {
						bfsQ.add(adjVertex);
						discovered.add(adjVertex);
						parent.put(adjVertex, current);
					}
				}
			} else {
				if (bfsQ.isEmpty())
					break;
				bfsQ.add(null);// end of a level
			}

		}

		return parent;

	}

	public static int gcd(int x, int y) {
		if (x % y == 0)
			return y;
		else
			return gcd(y, x % y);
	}

	static int mul(int a, int b, int n) {
		int r = 0;
		if (a < b) {
			int t = a;
			a = b;
			b = t;
		}
		while (b != 0) {
			if ((b & 1) != 0) {
				r += a;
				if (r >= n)
					r -= n;
			}
			b >>= 1;
			a += a;
			if (a >= n)
				a -= n;
		}
		return r;
	}

	static int modPow(int a0, int k, int mod) {
		int res = 1;
		int a = a0;
		while (k != 0) {
			if ((k & 1) != 0) {
				res = mul(res, a, mod);
			}
			a = mul(a, a, mod);
			k >>= 1;
		}
		return res;
	}

	static boolean MRIsPrime(int n) {
		if (n < 2)
			return false;
		if (n < (1 << 31))
			return MRIsPrime((int) n);
		int[] tests = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };
		int d = n - 1, s = 0;
		while ((d & 1) == 0) {
			d /= 2;
			s++;
		}
		for (int t : tests) {
			if (t >= n)
				break;

			int p = modPow(t, d, n);
			int corr = 1;
			if (p == 1)
				continue;

			for (int j = 0; j < s; j++) {
				if (p == n - 1)
					corr = 0;
				p = mul(p, p, n);
			}
			if (corr != 0)
				return false;
		}
		return true;
	}

	boolean isPrime(int n) {
		if (n < (1 << 31))
			return isPrime((int) n);
		return MRIsPrime(n);
	}

	public static Map<Integer, List<Integer>> getUnweightedUndirectedGraph(
			Scanner sc, int m) {

		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (adjList.containsKey(x)) {
				adjList.get(x).add(y);
			} else {
				List<Integer> xList = new ArrayList<Integer>();
				xList.add(y);
				adjList.put(x, xList);
			}

			if (adjList.containsKey(y)) {
				adjList.get(y).add(x);
			} else {
				ArrayList<Integer> yList = new ArrayList<Integer>();
				yList.add(x);
				adjList.put(y, yList);
			}

		}
		return adjList;

	}
}