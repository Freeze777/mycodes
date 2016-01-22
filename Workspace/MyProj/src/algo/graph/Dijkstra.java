package algo.graph;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


class MinHeapNode implements Comparable<MinHeapNode> {

	int priority;
	int vertex;

	public MinHeapNode(int vertex, int priority) {
		this.vertex = vertex;
		this.priority = priority;
	}

	public int compareTo(MinHeapNode o) {
		// TODO Auto-generated method stub
		return this.priority - o.priority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MinHeapNode other = (MinHeapNode) obj;
		if (vertex != other.vertex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MinHeapNode [priority=" + priority + ", vertex=" + vertex + "]";
	}

}

public class Dijkstra {
/*
 * handles both directed and undirected
 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		Map<Integer, List<AdjNode>> adjList =GraphUtility.getWeightedDirectedGraph(sc,n,m);
		int source = sc.nextInt();
System.out.println(adjList);
	 runDijkstrasAlgo(adjList, source, n);

	}

	private static void runDijkstrasAlgo(
		Map<Integer, List<AdjNode>> adjList, int source, int n) {
		PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<MinHeapNode>();
		Map<Integer, MinHeapNode> discovered = new HashMap<Integer, MinHeapNode>();
		Map<Integer,Integer> parent=new HashMap<Integer, Integer>();
		
		MinHeapNode s = new MinHeapNode(source, 0);
		minHeap.add(s);
		parent.put(s.vertex,null);
		discovered.put(s.vertex, s);

		while (!minHeap.isEmpty()) {
			MinHeapNode current = minHeap.remove();
			discovered.put(current.vertex, current);
			List<AdjNode> temp = adjList.get(current.vertex);
			if(temp!=null){
			for (AdjNode adjNode : temp) {
				if (!discovered.containsKey(adjNode.dest)) {
					MinHeapNode node = new MinHeapNode(adjNode.dest,
							(current.priority + adjNode.cost));
					minHeap.add(node);
					discovered.put(adjNode.dest, node);
					parent.put(adjNode.dest,current.vertex);
				} else {
					MinHeapNode node = new MinHeapNode(adjNode.dest, 0);
					if (minHeap.contains(node)) {
					node=discovered.get(node.vertex);
						if (node.priority> (current.priority + adjNode.cost))
						{	node.priority = current.priority + adjNode.cost;
							minHeap.remove(node);
							minHeap.add(node);
							parent.put(node.vertex,current.vertex);
						}
					}
				}

			}
			}
		}
	System.out.println(parent);
	}
}
