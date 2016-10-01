import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Vertex {
	ArrayList<Integer> adjacent;
	long weight;

	Vertex() {
		adjacent = new ArrayList<Integer>();
		weight = Long.MAX_VALUE;
	}
}

public class GravityTree {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Vertex[] vertex = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertex[i] = new Vertex();
		vertex[0].weight = 0;
		for (int i = 1; i < n; i++) {
			int v = in.nextInt() - 1;
			vertex[i].adjacent.add(v);
			vertex[v].adjacent.add(i);
		}
		boolean flag[] = new boolean[n];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		flag[0] = true;
		int parent[] = new int[n];
		parent[0] = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			int v = (int) queue.poll();
			for (int i = 0; i < vertex[v].adjacent.size(); i++) {
				if (!flag[vertex[v].adjacent.get(i)]
						&& vertex[vertex[v].adjacent.get(i)].weight > vertex[v].weight + 1) {
					vertex[vertex[v].adjacent.get(i)].weight = vertex[v].weight + 1;
					queue.add(vertex[v].adjacent.get(i));
					flag[vertex[v].adjacent.get(i)] = true;
					parent[vertex[v].adjacent.get(i)] = v;
				}
			}
		}
		int q = in.nextInt();
		while (q-- > 0) {
			int vf = in.nextInt() - 1;
			int von = in.nextInt() - 1;
			int temp;
			Stack<Integer> st = new Stack<Integer>();
			st.push(von);
			boolean visited[] = new boolean[n];
			visited[von] = true;
			long ans = 0;
			// computing list of subtree nodes
			ArrayList<Integer> subtree = new ArrayList<Integer>();
			subtree.add(von);
			while (!st.isEmpty()) {
				int v = st.pop();

				for (int i = 0; i < vertex[v].adjacent.size(); i++) {
					if (!visited[vertex[v].adjacent.get(i)]
							&& parent[v] != vertex[v].adjacent.get(i)) {
						flag[vertex[v].adjacent.get(i)] = true;
						st.push(vertex[v].adjacent.get(i));
						subtree.add(vertex[v].adjacent.get(i));
					}
				}
			}
			// computing d(vf,ve)
			for (int ve : subtree) {
				int lca = lca(vf, ve, parent, vertex);
				long dist = vertex[vf].weight + vertex[ve].weight - 2
						* vertex[lca].weight;
				ans += dist * dist;

			}

			System.out.println(ans);
		}
	}

	private static int lca(int vf, int ve, int[] parent, Vertex[] vertex) {
		long wvf = vertex[vf].weight;
		long wve = vertex[ve].weight;
		long diff = Math.abs(wvf - wve);
		while (diff-- > 0) {
			if (wvf > wve) {
				vf = parent[vf];
			} else {
				ve = parent[ve];
			}
		}
		while (vf != ve) {
			vf = parent[vf];
			ve = parent[ve];
		}
		return vf;
	}
}
