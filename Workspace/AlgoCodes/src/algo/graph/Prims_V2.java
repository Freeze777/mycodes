package algo.graph;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


class EdgeNode implements Comparable<EdgeNode> {


	int v1;
	int v2;
	int cost;
	
	public EdgeNode(int v1, int v2,int cost) {
		this.cost = cost;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgeNode other = (EdgeNode) obj;
		if (v1 != other.v1)
			return false;
		if (v2 != other.v2)
			return false;
		return true;
	}

	public int compareTo(EdgeNode o) {
		// TODO Auto-generated method stub
		if(this.cost>o.cost)
			return 1;
		else if(this.cost<o.cost)
			return -1;
		else	
		return (this.v1+this.v2)-(o.v1+o.v2);
	}

	@Override
	public String toString() {
		return "EdgeNode [v1=" + v1 + ", v2=" + v2 + ", cost=" + cost + "]";
	}

	


}

public class Prims_V2 {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	// int tst = sc.nextInt();

	// while (tst > 0) {
	int n = sc.nextInt();
	int m = sc.nextInt();
	Map<Integer, List<AdjNode>> adjList =GraphUtility.getWeightedUndirectedGraph(sc, n, m);

	int source = sc.nextInt();
	//System.out.println(adjList);
	runKruskalsAlgorithm(adjList,source);
}

private static void runKruskalsAlgorithm(
		Map<Integer, List<AdjNode>> adjList, int source) {
	// TODO Auto-generated method stub
		PriorityQueue<EdgeNode> minHeap = new PriorityQueue<EdgeNode>();
		HashMap<Integer,Boolean> discovered = new HashMap<Integer,Boolean>();
		long result = 0L;
		discovered.put(source, true);
		List<AdjNode> temp=adjList.get(source);
		
		for (AdjNode adjNode : temp) {
			minHeap.add(new EdgeNode(source,adjNode.dest,adjNode.cost));
		}
		while(!minHeap.isEmpty())
		{
			EdgeNode edge=minHeap.remove();	
			
			int current=edge.v2;
			if(!discovered.containsKey(current))
			{		
			result+=edge.cost;
			discovered.put(current,true);
			 temp=adjList.get(current);
			 
			 for (AdjNode adjNode : temp) {
				 if(!discovered.containsKey(adjNode.dest))
					minHeap.add(new EdgeNode(current,adjNode.dest,adjNode.cost));
				}
			}	
		}
	System.out.println(result);
}
}
