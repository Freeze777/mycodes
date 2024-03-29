package algo.graph;


import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


class MaxHeapComparator implements Comparator<MinNode>
{	public int compare(MinNode o1, MinNode o2) {
		
		return o2.priority-o1.priority;
	}


}


class MinNode implements Comparable<MinNode> {

	int priority;
	int vertex;

	public MinNode(int vertex, int priority) {
		this.vertex = vertex;
		this.priority = priority;
	}

	public int compareTo(MinNode o) {
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
		MinNode other = (MinNode) obj;
		if (vertex != other.vertex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MinHeapNode [priority=" + priority + ", vertex=" + vertex + "]";
	}

}

public class Prims {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		Map<Integer, List<AdjNode>> adjList =GraphUtility.getWeightedUndirectedGraph(sc, n, m);
		int source = sc.nextInt();
		runPrimsAlgorithm(adjList,source);
	
	}

	private static void runPrimsAlgorithm(
			Map<Integer, List<AdjNode>> adjList, int source) {
		PriorityQueue<MinNode> minHeap = new PriorityQueue<MinNode>();//pass maxHeapComparator incase of Maximum weight ST
		Map<Integer, MinNode> discovered = new HashMap<Integer, MinNode>();
		Map<Integer, Integer> parent = new HashMap<Integer,Integer>();
		long result = 0L;
		MinNode s = new MinNode(source, 0);
		minHeap.add(s);
		parent.put(source,null);
		discovered.put(s.vertex, s);

		while (!minHeap.isEmpty()) {
			MinNode current = minHeap.remove();
			result+=current.priority;
			List<AdjNode> temp = adjList.get(current.vertex);
			for (AdjNode adjNode : temp) {
				if (!discovered.containsKey(adjNode.dest)) {
					MinNode node = new MinNode(adjNode.dest,adjNode.cost);
					minHeap.add(node);
					discovered.put(adjNode.dest, node);
					parent.put(adjNode.dest,current.vertex);
				} else {
					MinNode node =discovered.get(adjNode.dest);
					if (minHeap.contains(node)) {
						if (node.priority >adjNode.cost) {//change to < in case max weight ST
							node.priority =adjNode.cost;
							minHeap.remove(node);
							minHeap.add(node);
							parent.put(adjNode.dest,current.vertex);
						}
					}
				}

			}

		}
	
	System.out.println(result);
	System.out.println(parent);

	}

}
