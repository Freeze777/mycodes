import java.math.BigInteger;
import java.util.*;

class AdjGraph {
	int v;
	LinkedList<Integer> adj[];
	int[] indegree;

	@SuppressWarnings("unchecked")
	public AdjGraph(int v) {
		this.v = v;
		this.indegree = new int[v];
		adj = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int u, int v) {
		adj[u].add(v);
		indegree[v]++;
	}

	public void alltopologicalSort(TreeSet<String> set) {
		boolean[] visited = new boolean[v];
		LinkedList<Integer> res = new LinkedList<Integer>();
		int i = 0;
		for (i = 0; i < v; i++)
			visited[i] = false;
		allTopologicalSortUtil(visited, res, set);

	}

	private void allTopologicalSortUtil(boolean[] visited,
			LinkedList<Integer> res, TreeSet<String> set) {

		int i = 0;
		int j;
		boolean flag = false;
		for (i = 0; i < v; i++) {
			if (indegree[i] == 0 && !visited[i]) {
				visited[i] = true;
				// remove this edge
				Iterator<Integer> itr = adj[i].listIterator();
				while (itr.hasNext()) {
					j = itr.next();
					indegree[j]--;
				}
				res.add(i);
				allTopologicalSortUtil(visited, res, set);
				visited[i] = false;
				itr = adj[i].listIterator();
				res.removeLast();
				while (itr.hasNext()) {
					j = itr.next();
					indegree[j]++;
				}
				flag = true;

			}
		}

		if (!flag) {
			ListIterator<Integer> it = res.listIterator();
			StringBuilder sb = new StringBuilder();
			while (it.hasNext()) {
				sb.append((it.next() + 1)+" ");
			}
			set.add(sb.toString().trim());
		}
	}
}

public class AllTopologicalSort {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		AdjGraph g = new AdjGraph(n);
		for (int i = 0; i < m; i++)
			g.addEdge(sc.nextInt() - 1, sc.nextInt() - 1);
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(sc.next()+" ");
		}
		TreeSet<String> set = new TreeSet<String>();
		g.alltopologicalSort(set);
		String ans=set.higher(sb.toString().trim());
		System.out.println(ans);

	}

}