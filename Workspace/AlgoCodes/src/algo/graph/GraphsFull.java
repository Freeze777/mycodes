package algo.graph;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class GraphsFull {
	
	static int N;
	static Scanner s = new Scanner(System.in);
	static int[] pop;
	static int[] comp;
	static List<Point> pairs;
	static boolean[] fixedFalse;
	static iGraph g;
	static long ans = 0;
	
	public static void main(String[] args) {
		N = s.nextInt();
		int M = s.nextInt();
		pop = new int[N];
		comp = new int[N];
		for(int i=0; i<N; i++){
			comp[i]=i;
			pop[i] = s.nextInt();
		}
		g = new iGraph(N);
		pairs = new ArrayList<Point>();
		for(int i=0; i<M; i++){
			int a = s.nextInt()-1;
			int b = s.nextInt()-1;
			if(getComp(a)==getComp(b)){
				pairs.add(new Point(a,b));
			}else{
				join(a,b);
				g.addUndirectedEdge(a, b);
			}
		}
		fixedFalse = new boolean[N];
		run(0);
		System.out.println(ans);
	}
	
	static Long[][] mem;
	
	static void run(int i){
		if(i>=pairs.size()){
			mem = new Long[N][2];
			ans=Math.max(ans, g.dp(0));
			return;
		}
		Point p = pairs.get(i);
		if(!fixedFalse[p.x] && !fixedFalse[p.y]){
			fixedFalse[p.x]=true;
			run(i+1);
			fixedFalse[p.x]=false;
			fixedFalse[p.y]=true;
			run(i+1);
			fixedFalse[p.y]=false;
		}else{
			run(i+1);
		}
	}
	
	static int getComp(int i){
		if(i==comp[i])
			return i;
		comp[i]=getComp(comp[i]);
		return comp[i];
	}
	
	static void join(int i, int j){
		if(getComp(i)<getComp(j)){
			comp[j]=comp[i];
		}else{
			comp[i]=comp[j];
		}
	}
	
	static  public class iGraph {

		List<Edge>[] edges;
		List<Integer>[] graph;
		BiconnectedComponents bc;
		Components c;

		public iGraph(int size) {
			edges = new List[size];
			graph = new List[size];
			for (int i = 0; i < size; i++) {
				edges[i] = new LinkedList<Edge>();
				graph[i] = new LinkedList<Integer>();
			}
			bc = new BiconnectedComponents();
		}

		public void addEdge(int v, int w) {
			graph[v].add(w);
		}

		public void addEfficientEdge(int v, int w, long weight) {
			edges[v].add(new Edge(w, weight));
		}

		public void addEdge(int v, int w, long weight) {
			graph[v].add(w);
			edges[v].add(new Edge(w, weight));
		}

		public void addUndirectedEdge(int v, int w) {
			addEdge(v, w);
			addEdge(w, v);
		}

		public void addUndirectedEdge(int v, int w, long weight) {
			addEdge(v, w, weight);
			addEdge(w, v, weight);
		}

		public void addEfficientUndirectedEdge(int v, int w, long weight) {
			addEfficientEdge(v, w, weight);
			addEfficientEdge(w, v, weight);
		}

		public static iGraph readUndirectedGraph(Scanner s) {
			int N = s.nextInt();
			int M = s.nextInt();
			iGraph g = new iGraph(N);
			for (int i = 0; i < M; i++) {
				int v = s.nextInt() - 1;
				int w = s.nextInt() - 1;
				g.addUndirectedEdge(v, w);
			}
			return g;
		}

		public static iGraph readUndirectedGraphWithWeight(Scanner s) {
			int N = s.nextInt();
			int M = s.nextInt();
			iGraph g = new iGraph(N);
			for (int i = 0; i < M; i++) {
				int v = s.nextInt() - 1;
				int w = s.nextInt() - 1;
				long weight = s.nextLong();
				g.addUndirectedEdge(v, w, weight);
			}
			return g;
		}

		public static iGraph readDirectedGraph(Scanner s) {
			int N = s.nextInt();
			int M = s.nextInt();
			iGraph g = new iGraph(N);
			for (int i = 0; i < M; i++) {
				int v = s.nextInt() - 1;
				int w = s.nextInt() - 1;
				g.addEdge(v, w);
			}
			return g;
		}

		public static iGraph readDirectedGraphWithWeight(Scanner s) {
			int N = s.nextInt();
			int M = s.nextInt();
			iGraph g = new iGraph(N);
			for (int i = 0; i < M; i++) {
				int v = s.nextInt() - 1;
				int w = s.nextInt() - 1;
				long weight = s.nextLong();
				g.addEdge(v, w, weight);
			}
			return g;
		}

		public List<List<Integer>> biconnectedComponents() {
			if (bc.components == null)
				bc.biconnectedComponents(graph);
			return bc.components;
		}

		public List<Integer> cutPoints() {
			if (bc.cutPoints == null)
				bc.biconnectedComponents(graph);
			return bc.cutPoints;
		}

		public List<Pair> bridges() {
			if (bc.bridges == null)
				bc.biconnectedComponents(graph);
			return bc.bridges;
		}

		private static class Edge {
			int v;
			long cost;

			public Edge(int node, long weight) {
				this.v = node;
				this.cost = weight;
			}
		}

		public static class Pair {
			int v, w;

			public Pair(int v, int w) {
				this.v = v;
				this.w = w;
			}
		}

		private void dfs(List<Integer>[] graph, boolean[] used, List<Integer> res,
				int u) {
			used[u] = true;
			for (int v : graph[u])
				if (!used[v])
					dfs(graph, used, res, v);
			res.add(u);
		}

		/* ---------------------------------------------- */

		public List<List<Integer>> stronglyConnectedComponents() {
			int n = graph.length;
			boolean[] used = new boolean[n];
			List<Integer> order = new ArrayList<>();
			for (int i = 0; i < n; i++)
				if (!used[i])
					dfs(graph, used, order, i);

			List<Integer>[] reverseGraph = new List[n];
			for (int i = 0; i < n; i++)
				reverseGraph[i] = new ArrayList<>();
			for (int i = 0; i < n; i++)
				for (int j : graph[i])
					reverseGraph[j].add(i);

			List<List<Integer>> components = new ArrayList<>();
			Arrays.fill(used, false);
			Collections.reverse(order);

			for (int u : order)
				if (!used[u]) {
					List<Integer> component = new ArrayList<>();
					dfs(reverseGraph, used, component, u);
					components.add(component);
				}

			return components;
		}

		/* ---------------------------------------------- */

		/* No checkea que sea valido */
		public List<Integer> eulerCycleUndirected(int u) {
			Set<Long> usedEdges = new HashSet<>();
			int n = graph.length;
			int[] curEdge = new int[n];
			List<Integer> res = new ArrayList<>();
			dfs(graph, curEdge, usedEdges, res, u);
			Collections.reverse(res);
			return res;
		}

		private void dfs(List<Integer>[] graph, int[] curEdge, Set<Long> usedEdges,
				List<Integer> res, int u) {
			while (curEdge[u] < graph[u].size()) {
				int v = graph[u].get(curEdge[u]++);
				if (usedEdges.add(((long) Math.min(u, v) << 32) + Math.max(u, v)))
					dfs(graph, curEdge, usedEdges, res, v);
			}
			res.add(u);
		}

		public List<Integer> eulerCycleDirected(int u) {
			int n = graph.length;
			int[] curEdge = new int[n];
			List<Integer> res = new ArrayList<>();
			dfs(graph, curEdge, res, u);
			Collections.reverse(res);
			return res;
		}

		private void dfs(List<Integer>[] graph, int[] curEdge, List<Integer> res,
				int u) {
			while (curEdge[u] < graph[u].size()) {
				dfs(graph, curEdge, res, graph[u].get(curEdge[u]++));
			}
			res.add(u);
		}

		/* ---------------------------------------------- */

		public List<List<Integer>> components() {
			if (c == null) {
				c = new Components(graph);
			}
			return c.components;
		}

		public boolean sameComponent(int u, int v) {
			if (c == null) {
				c = new Components(graph);
			}
			return c.componentsRepresentant[u] == c.componentsRepresentant[v];
		}

		private static class Components {
			List<Integer>[] graph;
			List<List<Integer>> components;
			int[] componentsRepresentant;

			public Components(List<Integer>[] graph) {
				this.graph = graph;
				components = new LinkedList<List<Integer>>();
				componentsRepresentant = new int[graph.length];
				getComponents();
			}

			private void getComponents() {
				boolean[] used = new boolean[graph.length];
				for (int i = 0; i < graph.length; i++) {
					if (!used[i]) {
						List<Integer> l = new LinkedList<Integer>();
						dfs(i, i, l, used);
						components.add(l);
					}
				}
			}

			private void dfs(int u, int r, List<Integer> l, boolean[] used) {
				used[u] = true;
				componentsRepresentant[u] = r;
				for (int v : graph[u])
					if (!used[v])
						dfs(v, r, l, used);
				l.add(u);
			}
		}

		/* ---------------------------------------------- */

		private static class BiconnectedComponents {
			List<Integer>[] graph;
			boolean[] visited;
			Stack<Integer> stack;
			int time;
			int[] tin;
			int[] lowlink;
			List<List<Integer>> components;
			List<Integer> cutPoints;
			List<Pair> bridges;

			public List<List<Integer>> biconnectedComponents(List<Integer>[] graph) {
				int n = graph.length;
				this.graph = graph;
				visited = new boolean[n];
				stack = new Stack<>();
				time = 0;
				tin = new int[n];
				lowlink = new int[n];
				components = new ArrayList<>();
				cutPoints = new ArrayList<>();
				bridges = new ArrayList<>();

				for (int u = 0; u < n; u++)
					if (!visited[u])
						dfs(u, -1);

				return components;
			}

			void dfs(int u, int p) {
				visited[u] = true;
				lowlink[u] = tin[u] = time++;
				stack.add(u);
				int children = 0;
				boolean cutPoint = false;
				for (int v : graph[u]) {
					if (v == p)
						continue;
					if (visited[v]) {
						// lowlink[u] = Math.min(lowlink[u], lowlink[v]);
						lowlink[u] = Math.min(lowlink[u], tin[v]);
					} else {
						dfs(v, u);
						lowlink[u] = Math.min(lowlink[u], lowlink[v]);
						cutPoint |= lowlink[v] >= tin[u];
						// if (lowlink[v] == tin[v])
						if (lowlink[v] > tin[u])
							bridges.add(new Pair(u, v));
						++children;
					}
				}
				if (p == -1)
					cutPoint = children >= 2;
				if (cutPoint)
					cutPoints.add(u);
				if (lowlink[u] == tin[u]) {
					List<Integer> component = new ArrayList<>();
					while (true) {
						int x = stack.pop();
						component.add(x);
						if (x == u)
							break;
					}
					components.add(component);
				}
			}
		}

		/* ---------------------------------------------- */

		public Long[][] floyd() {
			int size = edges.length;
			Long[][] ans = new Long[size][size];
			for (int i = 0; i < size; i++) {
				for (Edge e : edges[i]) {
					ans[i][e.v] = (ans[i][e.v] == null ? e.cost : Math.min(
							ans[i][e.v], e.cost));
				}
			}
			for (int k = 0; k < size; k++) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						if (ans[i][k] != null && ans[k][j] != null) {
							if (ans[i][j] == null
									|| ans[i][j] > ans[i][k] + ans[k][j]) {
								ans[i][j] = ans[i][k] + ans[k][j];
							}
						}
					}
				}
			}
			return ans;
		}

		/* ---------------------------------------------- */

		public List<Integer> topologicalSort() {
			int n = graph.length;
			boolean[] used = new boolean[n];
			List<Integer> res = new ArrayList<>();
			for (int i = 0; i < n; i++)
				if (!used[i])
					dfs(graph, used, res, i);
			Collections.reverse(res);
			return res;
		}

		/* ---------------------------------------------- */

		public long MinimumSpanningTreeValueElogV() {
			return mst(edges, new long[edges.length]);
		}

		private long mst(List<Edge>[] edges, long[] pred) {
			int n = edges.length;
			Arrays.fill(pred, -1);
			boolean[] used = new boolean[n];
			long[] prio = new long[n];
			Arrays.fill(prio, Integer.MAX_VALUE);
			prio[0] = 0;
			PriorityQueue<Long> q = new PriorityQueue<>();
			q.add(0L);
			long res = 0;

			while (!q.isEmpty()) {
				long cur = q.poll();
				int u = (int) cur;
				if (used[u])
					continue;
				used[u] = true;
				res += cur >>> 32;
				for (Edge e : edges[u]) {
					int v = e.v;
					if (!used[v] && prio[v] > e.cost) {
						prio[v] = e.cost;
						pred[v] = u;
						q.add(((long) prio[v] << 32) + v);
					}
				}
			}
			return res;
		}

		/* ---------------------------------------------- */

		public long MinimumSpanningTreeValueV2() {
			long[][] dist = new long[edges.length][edges.length];

			for (int i = 0; i < edges.length; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
				for (Edge e : edges[i]) {
					dist[i][e.v] = e.cost;
				}
			}
			return mstPrim(dist);
		}

		private long mstPrim(long[][] d) {
			int n = d.length;
			int[] prev = new int[n];
			long[] dist = new long[n];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[0] = 0;
			boolean[] visited = new boolean[n];
			long res = 0;
			for (int i = 0; i < n; i++) {
				int u = -1;
				for (int j = 0; j < n; j++) {
					if (!visited[j] && (u == -1 || dist[u] > dist[j]))
						u = j;
				}
				res += dist[u];
				visited[u] = true;
				for (int j = 0; j < n; j++) {
					if (!visited[j] && dist[j] > d[u][j]) {
						dist[j] = d[u][j];
						prev[j] = u;
					}
				}
			}
			return res;
		}

		/* ---------------------------------------------- */

		static final long INF = Long.MAX_VALUE / 2;

		public long[] bellmanFord(int s) {
			long[] ans = new long[edges.length];
			int[] pred = new int[edges.length];
			if (bellmanFord(s, ans, pred)) {
				return ans;
			}
			return null;
		}

		private boolean bellmanFord(int s, long[] dist, int[] pred) {
			Arrays.fill(pred, -1);
			Arrays.fill(dist, INF);
			System.out.println("diegui puti, vas a perder");
			dist[s] = 0;
			int n = edges.length;
			boolean updated = false;
			for (int step = 0; step < n; step++) {
				updated = false;
				for (int u = 0; u < n; u++) {
					if (dist[u] == INF)
						continue;
					for (Edge e : edges[u]) {
						if (dist[e.v] > dist[u] + e.cost) {
							dist[e.v] = dist[u] + e.cost;
							dist[e.v] = Math.max(dist[e.v], -INF);
							pred[e.v] = u;
							updated = true;
						}
					}
				}
				if (!updated)
					break;
			}
			// if updated is true then a negative cycle exists
			return updated == false;
		}

		public int[] findNegativeCycle() {
			int n = edges.length;
			int[] pred = new int[n];
			Arrays.fill(pred, -1);
			long[] dist = new long[n];
			int last = -1;
			for (int step = 0; step < n; step++) {
				last = -1;
				for (int u = 0; u < n; u++) {
					if (dist[u] == INF)
						continue;
					for (Edge e : edges[u]) {
						if (dist[e.v] > dist[u] + e.cost) {
							dist[e.v] = Math.max(dist[u] + e.cost, -INF);
							dist[e.v] = Math.max(dist[e.v], -INF);
							pred[e.v] = u;
							last = e.v;
						}
					}
				}
				if (last == -1)
					return null;
			}
			for (int i = 0; i < n; i++) {
				last = pred[last];
			}
			int[] p = new int[n];
			int cnt = 0;
			for (int u = last; u != last || cnt == 0; u = pred[u]) {
				p[cnt++] = u;
			}
			int[] cycle = new int[cnt];
			for (int i = 0; i < cycle.length; i++) {
				cycle[i] = p[--cnt];
			}
			return cycle;
		}

		/* ---------------------------------------------- */

		/**
		 * row 0 - return values of distance row 1 - return previous node
		 */

		public long[][] dijkstra(int start) {
			long[][] ans = new long[2][edges.length];
			shortestPaths(start, ans[0], ans[1]);
			// shortestPaths2(start, ans[0], ans[1]); for long values in cost;
			return ans;
		}

		private void shortestPaths(int s, long[] prio, long[] pred) {
			Arrays.fill(pred, -1);
			Arrays.fill(prio, Integer.MAX_VALUE);
			prio[s] = 0;
			PriorityQueue<Long> q = new PriorityQueue<>();
			q.add((long) s);
			while (!q.isEmpty()) {
				long cur = q.remove();
				int curu = (int) cur;
				if (cur >>> 32 != prio[curu])
					continue;
				for (Edge e : edges[curu]) {
					int v = e.v;
					long nprio = prio[curu] + e.cost;
					if (prio[v] > nprio) {
						prio[v] = nprio;
						pred[v] = curu;
						q.add(((long) nprio << 32) + v);
					}
				}
			}
		}

		private void shortestPaths2(int s, long[] prio, long[] pred) {
			Arrays.fill(pred, -1);
			Arrays.fill(prio, Integer.MAX_VALUE);
			prio[s] = 0;
			PriorityQueue<Edge> q = new PriorityQueue<>(new Comparator<Edge>() {
				public int compare(Edge o1, Edge o2) {
					return Long.compare(o1.cost, o2.cost);
				}
			});
			q.add(new Edge(s, 0));
			while (!q.isEmpty()) {
				Edge ed = q.remove();
				long cur = ed.cost;
				int curu = ed.v;
				if (cur != prio[curu])
					continue;
				for (Edge e : edges[curu]) {
					int v = e.v;
					long nprio = prio[curu] + e.cost;
					if (prio[v] > nprio) {
						prio[v] = nprio;
						pred[v] = curu;
						q.add(new Edge(v, nprio));
					}
				}
			}
		}

		/* ---------------------------------------------- */

		public List<Integer> findCenters() {
			int n = graph.length;
			List<Integer> leaves = new ArrayList<>();
			int[] degree = new int[n];
			for (int i = 0; i < n; i++) {
				degree[i] = graph[i].size();
				if (degree[i] <= 1) {
					leaves.add(i);
				}
			}
			int removedLeaves = leaves.size();
			while (removedLeaves < n) {
				List<Integer> nleaves = new ArrayList<>();
				for (int u : leaves) {
					for (int v : graph[u]) {
						if (--degree[v] == 1) {
							nleaves.add(v);
						}
					}
				}
				leaves = nleaves;
				removedLeaves += leaves.size();
			}
			return leaves;
		}

		/**
		 * 
		 * @return vertex that has all its subtrees sizes <= n/2
		 */
		public int findCentroid() {
			return findCentroid(0, -1);
		}

		private int findCentroid(int u, int p) {
			int n = graph.length;
			int cnt = 1;
			boolean goodCenter = true;
			for (int v : graph[u]) {
				if (v == p)
					continue;
				int res = findCentroid(v, u);
				if (res >= 0)
					return res;
				int size = -res;
				goodCenter &= size <= n / 2;
				cnt += size;
			}
			goodCenter &= n - cnt <= n / 2;
			return goodCenter ? u : -cnt;
		}

		/**
		 * Works only with undirected trees
		 * 
		 * @return
		 */
		public int diameter() {
			int furthestVertex = (int) dfs(graph, 0, -1, 0);
			return (int) (dfs(graph, furthestVertex, -1, 0) >>> 32);
		}

		private long dfs(List<Integer>[] tree, int u, int p, int depth) {
			long res = ((long) depth << 32) + u;
			for (int v : tree[u])
				if (v != p)
					res = Math.max(res, dfs(tree, v, u, depth + 1));
			return res;
		}

		/* -------------------------------- */

		/**
		 * 
		 * @param n1
		 *            - from 0..(n1-1) sources
		 * @param n2
		 *            - from n1..(n1+n2-1) destination
		 * @return
		 */

		public int maxMatchingEV(int n1, int n2) {
			int[] matching = new int[n2];
			Arrays.fill(matching, -1);
			int matches = 0;
			for (int u = 0; u < n1; u++) {
				if (findPath(graph, u, matching, new boolean[n1]))
					++matches;
			}
			return matches;
		}

		private boolean findPath(List<Integer>[] graph, int u1, int[] matching,
				boolean[] vis) {
			vis[u1] = true;
			for (int v : graph[u1]) {
				int u2 = matching[v];
				if (u2 == -1 || !vis[u2] && findPath(graph, u2, matching, vis)) {
					matching[v] = u1;
					return true;
				}
			}
			return false;
		}

		/* -------------------------------- */

		public int maxFlowV2Flow(int s, int t) {
			long[][] cap = new long[graph.length][graph.length];
			for (int i = 0; i < graph.length; i++) {
				for (Edge e : edges[i]) {
					cap[i][e.v] = e.cost;
				}
			}
			for (int flow = 0;;) {
				long df = findPath(cap, new boolean[cap.length], s, t,
						Long.MAX_VALUE);
				if (df == 0)
					return flow;
				flow += df;
			}
		}

		private long findPath(long[][] cap, boolean[] vis, int u, int t, long f) {
			if (u == t)
				return f;
			vis[u] = true;
			for (int v = 0; v < vis.length; v++)
				if (!vis[v] && cap[u][v] > 0) {
					long df = findPath(cap, vis, v, t, Math.min(f, cap[u][v]));
					if (df > 0) {
						cap[u][v] -= df;
						cap[v][u] += df;
						return df;
					}
				}
			return 0;
		}

		/* -------------------------------- */

		public long maxFlowN3(int s, int t) {
			long[][] cap = new long[graph.length][graph.length];
			for (int i = 0; i < graph.length; i++) {
				for (Edge e : edges[i]) {
					cap[i][e.v] = e.cost;
				}
			}
			return maxFlow(s, t, cap);
		}

		private long maxFlow(int s, int t, long[][] cap) {
			int n = cap.length;

			long[] h = new long[n];
			h[s] = n - 1;

			int[] maxh = new int[n];

			long[][] f = new long[n][n];
			long[] e = new long[n];

			for (int i = 0; i < n; ++i) {
				f[s][i] = cap[s][i];
				f[i][s] = -f[s][i];
				e[i] = cap[s][i];
			}

			for (int sz = 0;;) {
				if (sz == 0) {
					for (int i = 0; i < n; ++i)
						if (i != s && i != t && e[i] > 0) {
							if (sz != 0 && h[i] > h[maxh[0]])
								sz = 0;
							maxh[sz++] = i;
						}
				}
				if (sz == 0)
					break;
				while (sz != 0) {
					int i = maxh[sz - 1];
					boolean pushed = false;
					for (int j = 0; j < n && e[i] != 0; ++j) {
						if (h[i] == h[j] + 1 && cap[i][j] - f[i][j] > 0) {
							long df = Math.min(cap[i][j] - f[i][j], e[i]);
							f[i][j] += df;
							f[j][i] -= df;
							e[i] -= df;
							e[j] += df;
							if (e[i] == 0)
								--sz;
							pushed = true;
						}
					}
					if (!pushed) {
						h[i] = Integer.MAX_VALUE;
						for (int j = 0; j < n; ++j)
							if (h[i] > h[j] + 1 && cap[i][j] - f[i][j] > 0)
								h[i] = h[j] + 1;
						if (h[i] > h[maxh[0]]) {
							sz = 0;
							break;
						}
					}
				}
			}

			int flow = 0;
			for (int i = 0; i < n; i++)
				flow += f[s][i];

			return flow;
		}
		
		/* ------------------------------------ */
		
		
		
		public long dp(int u){
			return dfs(u, u, false);
		}
		
		private long dfs(int u, int p, boolean prevSelected){
			int x = prevSelected?1:0;
			if(mem[u][x]==null){
				long sumSelected = 0;
				long sumNoSelected = 0;
				for(int v : graph[u]){
					if(v!=p){
						sumNoSelected+=dfs(v,u,false);
					}
				}
				if(!prevSelected && !fixedFalse[u]){
					sumSelected+=pop[u];
					for(int v : graph[u]){
						if(v!=p){
							sumSelected+=dfs(v,u,true);
						}
					}
					sumNoSelected=Math.max(sumNoSelected, sumSelected);
				}
				mem[u][x]=sumNoSelected;
			}
			return mem[u][x];
		}
		
	}

}