package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS {
	static Map<Integer, Integer> discovered_level = new HashMap<Integer, Integer>();
	static Map<Integer, Set<Integer>> level_node_list = new HashMap<Integer, Set<Integer>>();
	static Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
	static Queue<Integer> bfsQ = new LinkedList<Integer>();
	static long[] node_val;

	public static Map<Integer, List<Integer>> getUnweightedDirectedGraph(
			Scanner sc, int m) {

		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			if (adjList.containsKey(x)) {
				adjList.get(x).add(y);
			} else {
				ArrayList<Integer> xList = new ArrayList<Integer>();
				xList.add(y);
				adjList.put(x, xList);
			}

		}
		return adjList;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int m = n - 1;
			int q = sc.nextInt();
			node_val = new long[n];
			for (int i = 0; i < n; i++)
				node_val[i] = sc.nextLong();

			Map<Integer, List<Integer>> adjList = getUnweightedDirectedGraph(
					sc, m);

			breadthFirstSearch(0, adjList, n);
			for (int i = 0; i < q; i++) {
				int u=sc.nextInt()-1;
				int l=sc.nextInt();
				System.out.println(solve(u,l));
				
			}
			

			bfsQ.clear();
			parent.clear();
			discovered_level.clear();
			level_node_list.clear();
		}

	}

	private static long solve(int u, int l) {
		if(discovered_level.get(u)>l)
			return 0;
		if(discovered_level.get(u)==l)
			return node_val[u];
		Set<Integer> nodes=level_node_list.get(l);
		long ans=0;
		for(Integer node:nodes){
			if(isChild(u,node))
				ans+=node_val[node];			
		}
		return ans;
		
	}

	private static boolean isChild(Integer u, Integer node) {
		Integer node_parent=parent.get(node);
		while(node_parent!=null && node_parent!=u){
			node_parent=parent.get(node_parent);
		}
		return (node!=null);
	}

	public static void breadthFirstSearch(int s,
			Map<Integer, List<Integer>> adjList, int n) {
		int level = 2;

		bfsQ.add(s);
		bfsQ.add(null);
		Set<Integer> level1=new HashSet<Integer>();
		level1.add(s);
		level_node_list.put(1,level1);
		discovered_level.put(s, 1);
		parent.put(s, null);

		while (!bfsQ.isEmpty()) {
			Integer current = bfsQ.remove();

			if (current != null) {
				List<Integer> currList = adjList.get(current);
				if (currList != null) {
					for (Integer adjVertex : currList) {
						if (!discovered_level.containsKey(adjVertex)) {
							bfsQ.add(adjVertex);
							discovered_level.put(adjVertex, (level));
							if(!level_node_list.containsKey(level))
								level_node_list.put(level,new HashSet<Integer>());
							level_node_list.get(level).add(adjVertex);
							parent.put(adjVertex, current);
						}
					}
				}
			} else {
				if (bfsQ.isEmpty())
					break;
				level++;
				bfsQ.add(null);// end of a level
			}

		}

		System.out.println(parent);
		System.out.println(discovered_level);
		System.out.println(level_node_list);

	}
}