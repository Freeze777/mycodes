package hacker.earth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MattGraphBook {
	static class DFSVertex implements Comparable<DFSVertex> {
		int id;
		int finish;
		int start;

		public DFSVertex(int id) {
			this.id = id;
			this.finish = -1;
			this.start = -1;
		}

		@Override
		public String toString() {
			return "DFSVertex [id=" + id + ", finish=" + finish + ", start="
					+ start + "]\n";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DFSVertex other = (DFSVertex) obj;
			if (id != other.id)
				return false;
			return true;
		}

		public int compareTo(DFSVertex o) {
			return o.finish - this.finish;
		}

	}

	static int time = 1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		Map<DFSVertex, List<DFSVertex>> adjList = new HashMap<DFSVertex, List<DFSVertex>>();

		for (int i = 0; i < m; i++) {
			DFSVertex x = new DFSVertex(sc.nextInt());
			DFSVertex y = new DFSVertex(sc.nextInt());
			if (adjList.containsKey(x)) {
				if (!adjList.get(x).contains(y))// to avoid repaeted edges
					adjList.get(x).add(y);
			} else {
				List<DFSVertex> xList = new ArrayList<DFSVertex>();
				xList.add(y);
				adjList.put(x, xList);
			}

			// comment these part for directed graphs
			if (adjList.containsKey(y)) {
				if (!adjList.get(y).contains(x))
					adjList.get(y).add(x);
			} else {
				List<DFSVertex> yList = new ArrayList<DFSVertex>();
				yList.add(x);
				adjList.put(y, yList);
			}//

		}
		DFSVertex v = new DFSVertex(sc.nextInt());
		List<DFSVertex> l = adjList.get(v);
		for (DFSVertex x : l) {
			adjList.get(x).remove(v);
		}
		adjList.remove(v);
		DFSVertex source = (DFSVertex) adjList.keySet().toArray()[0];

		Set<DFSVertex> discovered = new HashSet<DFSVertex>();
		Map<DFSVertex, DFSVertex> parent = new HashMap<DFSVertex, DFSVertex>();

		discovered.add(source);
		parent.put(source, null);
		DepthFirstSearch(source, adjList, discovered, parent);

		// System.out.println(parent);
		System.out.println((discovered.size() == n - 1) ? "Connected"
				: "Not Connected");
		// topologicalSortForDAG(discovered);
	}

	private static void DepthFirstSearch(DFSVertex current,
			Map<DFSVertex, List<DFSVertex>> adjList, Set<DFSVertex> discovered,
			Map<DFSVertex, DFSVertex> parent) {
		current.start = time++;
		List<DFSVertex> currList = adjList.get(current);

		for (DFSVertex adjVertex : currList) {
			if (!discovered.contains(adjVertex)) {
				discovered.add(adjVertex);
				parent.put(adjVertex, current);
				DepthFirstSearch(adjVertex, adjList, discovered, parent);
			}

		}
		current.finish = time++;

	}

	private static void topologicalSortForDAG(Set<DFSVertex> discovered) {
		Set<DFSVertex> set = new TreeSet<DFSVertex>(discovered);
		System.out.println(set);
	}

}