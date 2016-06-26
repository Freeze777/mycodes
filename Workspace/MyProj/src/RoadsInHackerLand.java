import java.io.*;
import java.util.*;

class Edge {
	int destinationV;
	long weight;

	Edge(int d, long w) {
		destinationV = d;
		weight = w;
	}
}

class Vertex implements Comparable<Vertex> {
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

public class RoadsInHackerLand {

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
				s = queue.peek().vertex;
				queue.remove();
				// System.out.println(" VERTEX WEIGHT IS "+v[s].weight+"ss"+s);
				Iterator<Edge> it = v[s].e.listIterator();
				while (it.hasNext()) {
					Edge no = it.next();
					if (v[s].weight == Long.MAX_VALUE)
						break;
					if (v[no.destinationV].weight > v[s].weight + no.weight
							&& v[s].weight != Long.MAX_VALUE) {
						v[no.destinationV].weight = v[s].weight + no.weight;

						queue.remove(v[no.destinationV]);
						queue.add(v[no.destinationV]);
						parent.put(no.destinationV, s);
					}

				}
			}
			for (int i = 0; i < v.length; i++) {
				for (int j = i + 1; j < v.length; j++) {
					int index = (int) Math.abs(v[i].weight - v[j].weight);
					if (parent.containsKey(i) && parent.get(i) == j) {
						if (bin[index] == 0)
							bin[index] = 1;
						else {
							int k = index;
							for (; k < bin.length && bin[k] == 1;)
								bin[k++] = 0;
							bin[k] = 1;
						}
					} else if (parent.containsKey(j) && parent.get(j) == i) {
						if (bin[index] == 0)
							bin[index] = 1;
						else {
							int k = index;
							for (; k < bin.length && bin[k] == 1;)
								bin[k++] = 0;
							bin[k] = 1;
						}
					}
				}
			}
			
			queue.clear();
			for (int i = 0; i < v.length; i++) {
				v[i].weight = Long.MAX_VALUE;
				queue.add(v[i]);
			}
			
		}
		for (int i = 0; i < 10; i++) {
			System.out.print(bin[i]+" ");
		}

	}

}