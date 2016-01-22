package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GraphUtility {

	public static Map<Integer, List<Integer>> getUnweightedUndirectedGraph(
			Scanner sc, int n, int m) {

		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (adjList.containsKey(x)) {
					adjList.get(x).add(y);
			} else {
				List<Integer> xList = new ArrayList<Integer>();
				xList.add(y);
				adjList.put(x, xList);
			}

			if (adjList.containsKey(y)) {
					adjList.get(y).add(x);
			} else {
				ArrayList<Integer> yList = new ArrayList<Integer>();
				yList.add(x);
				adjList.put(y, yList);
			}

		}
		return adjList;

	}

	public static Map<Integer, List<Integer>> getUnweightedDirectedGraph(
			Scanner sc, int n, int m) {

		Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
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

	public static Map<Integer, List<AdjNode>> getWeightedUndirectedGraph(
			Scanner sc, int n, int m) {
		Map<Integer, List<AdjNode>> adjList = new HashMap<Integer, List<AdjNode>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int r = sc.nextInt();
			AdjNode xy = new AdjNode(y, r);
			AdjNode yx = new AdjNode(x, r);
			if (adjList.containsKey(x)) {
				List<AdjNode> xList = adjList.get(x);
					xList.add(xy);
			
			} else {
				List<AdjNode> xList = new ArrayList<AdjNode>();
				xList.add(xy);
				adjList.put(x, xList);
			}
			if (adjList.containsKey(y)) {
				List<AdjNode> yList = adjList.get(y);
				yList.add(yx);
			
			} else {
				List<AdjNode> yList = new ArrayList<AdjNode>();
				yList.add(yx);
				adjList.put(y, yList);
			}

		}
		return adjList;

	}

	public static Map<Integer, List<AdjNode>> getWeightedDirectedGraph(
			Scanner sc, int n, int m) {
		Map<Integer, List<AdjNode>> adjList = new HashMap<Integer, List<AdjNode>>();

		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int r = sc.nextInt();
			AdjNode xy = new AdjNode(y, r);
			if (adjList.containsKey(x)) {
				List<AdjNode> xList = adjList.get(x);
					xList.add(xy);
				
			} else {
				List<AdjNode> xList = new ArrayList<AdjNode>();
				xList.add(xy);
				adjList.put(x, xList);
			}

		}
		return adjList;

	}
	public static Map<Integer, List<Integer>> getReversedGraph(Map<Integer, List<Integer>> adjList)
	{	Map<Integer, List<Integer>> revAdjList=new HashMap<Integer, List<Integer>>();
		for (Integer vertex :adjList.keySet()) {
			List<Integer> list=adjList.get(vertex);
			for (Integer adjVertex : list) {
				if (revAdjList.containsKey(adjVertex)) {
					List<Integer> adjVertexList = revAdjList.get(adjVertex);
					adjVertexList.add(vertex);
					
				} else {
					List<Integer> adjVertexList = new ArrayList<Integer>();
					adjVertexList.add(vertex);
					revAdjList.put(adjVertex,adjVertexList);
				}

			}
			
		}
		
		return revAdjList;
		
		
		
		
	}

	
}
