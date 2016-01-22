package algo.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * DFS detecting cycles
 * keeps track of finish times
 *  
 * */
public class DFS_Basic {
	static boolean cycle = false;
	static Map<Integer, Integer> finish2Vertex = new TreeMap<Integer, Integer>(
			new Comparator<Integer>() {

				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}

			});

	static Map<Integer, Integer> balance = new TreeMap<Integer, Integer>();
	static Map<Integer, Integer> subTreeCount = new TreeMap<Integer, Integer>();
	static int time = 1;
	static int v = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tst = sc.nextInt();
		while(tst>0){
		int n= sc.nextInt();
		v = n;
		int m=n-1;
		Map<Integer, List<Integer>> adjList = GraphUtility
				.getUnweightedUndirectedGraph(sc, n, m);
	

		//System.out.println("Adjacency List:"+adjList);
		DFSHelper(adjList, 1);
		time = 1;
		int max = Integer.MIN_VALUE;
		/*for (Integer adjVertex : adjList.get(source)) {
			if (balance.get(adjVertex) > max) {
				max = balance.get(adjVertex);

			}

		}
		balance.put(source, max - 1);*/
		//System.out.println("Balance Map:"+balance);
		int min=Integer.MAX_VALUE;
		int key=0;
		for(Integer i :balance.keySet()) {
			if(min>balance.get(i))
				{min=balance.get(i);
				key=i;
				}
				}
		
		System.out.println(min+" "+key);
		tst--;
	}
		}
	
	public static void DFSHelper(Map<Integer, List<Integer>> adjList, int source) {
		// TODO Auto-generated method stub
		Map<Integer, Boolean> discovered = new HashMap<Integer, Boolean>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		discovered.put(source, false);
		parent.put(source, null);
		// System.out.println("\nDFS from source :" + source);
		DepthFirstSearch(source, adjList, discovered, parent);

		for (Integer vertex : adjList.keySet()) {
			if (!discovered.containsKey(vertex)) {
				discovered.put(vertex, false);
				parent.put(vertex, null);
				DepthFirstSearch(vertex, adjList, discovered, parent);
			}
		}

		// System.out.println("[Parent(u)=v] :" + parent);
		// System.out.println("[t=Finish(u)] :" + finish2Vertex);

	}

	private static void DepthFirstSearch(int current,
			Map<Integer, List<Integer>> adjList,
			Map<Integer, Boolean> discovered, Map<Integer, Integer> parent) {
		//System.out.println("DFS at:" + current);
		List<Integer> currList = adjList.get(current);
		if (currList != null) {

			for (Integer adjVertex : currList) {
				if (!discovered.containsKey(adjVertex)) {
					discovered.put(adjVertex, false);
					parent.put(adjVertex, current);
					DepthFirstSearch(adjVertex, adjList, discovered, parent);
				}

			}
			if (currList.size() == 1)// leaf vertex computations
			{
				balance.put(current, v - 1);
				subTreeCount.put(current, 1);
				
			} else {
				int count = 0;
				int max = Integer.MIN_VALUE;
				int temp = 0;
				for (Integer adjVertex : currList) {
					if (!adjVertex.equals(parent.get(current))) {

						count += subTreeCount.get(adjVertex);//total subtree count around current excluding parent

						if (subTreeCount.get(adjVertex) > max)
							max = subTreeCount.get(adjVertex);//max subtree count around current vertex excluding parent

					}
				}
				subTreeCount.put(current, count + 1);//updating subtree count of current
				
				temp = v - count - 1;//parents subtree count
			//	System.out.println("Max:"+max);
				balance.put(current, Math.max(temp, max));//updating balnce of current as the max subtree count of adjacent vertices

			}
			//System.out.println(current + " " + balance.get(current));
		}
		discovered.put(current, true);
		finish2Vertex.put(time++, current);
	}

	public static void printStronglyConnectedComponents(
			Map<Integer, List<Integer>> revAdjList, int n) {
		DFSHelper(revAdjList, finish2Vertex.get(n));

	}
}