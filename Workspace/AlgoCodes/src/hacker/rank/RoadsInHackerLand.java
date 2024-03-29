package hacker.rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Edge {
	int destinationV;
	long weight;

	Edge(int d, long w) {
		destinationV = d;
		weight = w;
	}
}



public class RoadsInHackerLand {
	static class Vertex implements Comparable<Vertex> {
		int vertex;
		ArrayList<Edge> e;
		long weight;

		Vertex(int v) {
			vertex = v;
			e = new ArrayList<Edge>();
			weight = Long.MAX_VALUE;
		}

		@Override
		public int compareTo(Vertex v) {
			return Long.compare(this.weight, v.weight);
		}
	}

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */

		Scanner in = new Scanner(System.in);
		int[] bin = new int[200001];
		int n, m;
		n = in.nextInt();
		m = in.nextInt();
		Vertex v[] = new Vertex[n];
		int maxLen = -1;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		for (int i = 0; i < n; i++) {
			v[i] = new Vertex(i);
			queue.add(v[i]);
		}

		for (int i = 0; i < m; i++) {
			int s = in.nextInt() - 1;
			int d = in.nextInt() - 1;
			int w = in.nextInt();
			Edge e = new Edge(d, w);
			v[s].e.add(e);
			e = new Edge(s, w);
			v[d].e.add(e);
		}
		for (int s = 0; s < v.length; s++) {
			// Create a queue for BFS
			v[s].weight = 0;
			queue.remove(v[s]);
			queue.add(v[s]);
			Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
			while (queue.size() != 0) {
				int t = queue.peek().vertex;
				queue.remove();
				// System.out.println(" VERTEX WEIGHT IS "+v[s].weight+"ss"+s);
				Iterator<Edge> it = v[t].e.listIterator();
				while (it.hasNext()) {
					Edge no = it.next();
					if (v[t].weight == Long.MAX_VALUE)
						break;
					if (v[no.destinationV].weight > v[t].weight + no.weight
							&& v[t].weight != Long.MAX_VALUE) {
						v[no.destinationV].weight = v[t].weight + no.weight;

						queue.remove(v[no.destinationV]);
						queue.add(v[no.destinationV]);
						parent.put(no.destinationV, t);
					}

				}
			}

			for (int i = 0; i < v.length; i++) {
				if (i == s)
					continue;
				int t = i;
				do {
					int tt = parent.get(t);
					int index = (int) Math.abs(v[t].weight - v[tt].weight);
					if (bin[index] == 0) {
						bin[index] = 1;
						maxLen = Math.max(maxLen, index);
					} else {
						int k = index;
						for (; k < bin.length && bin[k] == 1;)
							bin[k++] = 0;
						bin[k] = 1;
						maxLen = Math.max(maxLen, k);
					}
					t = tt;
				} while (s != t);

			}

			queue.clear();
			for (int i = 0; i < v.length; i++) {
				v[i].weight = Long.MAX_VALUE;
				queue.add(v[i]);
			}

		}
		for (int i = maxLen; i > 0; i--)
			System.out.print(bin[i]);
		

	}
}