package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		Map<Integer, List<Integer>> adjList=GraphUtility.getUnweightedUndirectedGraph(sc,n,m);
	
		int s = sc.nextInt();

		breadthFirstSearch(s, adjList, 6, n);

	}

	public static void breadthFirstSearch(int s,
			Map<Integer, List<Integer>> adjList, int edgeLength, int n) {
		Map<Integer, Integer> discovered = new HashMap<Integer, Integer>();
		Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
		Queue<Integer> bfsQ = new LinkedList<Integer>();

		int level = 1;

		bfsQ.add(s);
		bfsQ.add(null);

		discovered.put(s, 0);
		parent.put(s, null);

		while (!bfsQ.isEmpty()) {
			Integer current = bfsQ.remove();

			if (current != null) {
			List<Integer> currList = adjList.get(current);
				if (currList != null) {
					for (Integer adjVertex : currList) {
						if (!discovered.containsKey(adjVertex)) {
							bfsQ.add(adjVertex);
							discovered.put(adjVertex, (level * edgeLength));
							parent.put(adjVertex, current);
						}
					}
				}
			} else {
				if (bfsQ.isEmpty())
					break;
				level++;
				bfsQ.add(null);//end of a level
			}

		}

	
		System.out.println(parent);
		System.out.println(discovered);

	}
}