package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;



class GNode {

	int pno;
	int h;
}

public class BFSLight {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = n - 1;
		int q = sc.nextInt();
		GNode[] platform = new GNode[n];
		for (int i = 0; i < platform.length; i++) {
			platform[i] = new GNode();
			platform[i].pno = i;
			platform[i].h = sc.nextInt();
		}

		Map<GNode, List<GNode>> adjList = new HashMap<GNode, List<GNode>>();
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			if (adjList.containsKey(platform[x - 1])) {
				adjList.get(platform[x - 1]).add(platform[y - 1]);
			} else {
				ArrayList<GNode> xList = new ArrayList<GNode>();
				xList.add(platform[y - 1]);
				adjList.put(platform[x - 1], xList);
			}

		}
		for (int i = 0; i < q; i++) {
			int cap = sc.nextInt();
			GNode curr = platform[0];
			int[] max = new int[1];
			max[0] = -1;
			int count = 0;
			dfs(adjList, curr, cap, max, count);

			System.out.println(max[0] + 1);
		}

	}

	private static void dfs(Map<GNode, List<GNode>> adjList, GNode curr,
			int cap, int[] max, int count) {

		if (curr == null)
			return;
		List<GNode> xList = adjList.get(curr);
		if (xList != null) {
			for (GNode gNode : xList) {
				if (Math.abs(gNode.h - curr.h) <= cap) {

					dfs(adjList, gNode, cap, max, count + 1);
					if (max[0] < count)
						max[0] = count;

				}
			}

		}

	}

}