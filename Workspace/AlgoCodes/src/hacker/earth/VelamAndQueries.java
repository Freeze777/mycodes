package hacker.earth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;


public class VelamAndQueries {
	static Map<Integer, ArrayList<Integer>> levelNode;
	static Map<Integer, Integer> level;
	static Map<Integer, Integer> parent;
	static Map<Integer, ArrayList<Integer>> adjacentList;
	static Map<Integer, Set<Integer>> subTreeMap;

	public static void main(String args[]) throws Exception {
		Scanner s = new Scanner(System.in);
		int tc = s.nextInt();
		while (tc-- > 0) {
			levelNode = new HashMap<Integer, ArrayList<Integer>>();
			level = new HashMap<Integer, Integer>();
			parent = new HashMap<Integer, Integer>();
			int n = s.nextInt();
			int q = s.nextInt();
			int nodes[] = new int[n];
			int max_level[] = { 1 };
			adjacentList = new HashMap<Integer, ArrayList<Integer>>();
			for (int i = 0; i < n; i++) {
				nodes[i] = s.nextInt();
				adjacentList.put(i, new ArrayList<Integer>());
			}
			for (int i = 0; i < n - 1; i++) {
				int st = s.nextInt() - 1;
				int d = s.nextInt() - 1;
				adjacentList.get(st).add(d);
				adjacentList.get(d).add(st);
			}
			boolean visited[] = new boolean[n];
			subTreeMap = new HashMap<Integer, Set<Integer>>();
			subTreeSets(adjacentList, 0, visited, subTreeMap);
			// System.out.println(subTreeMap);
			visited = new boolean[n];
			bfs(nodes, visited, 0, max_level);
			solve(s, q, nodes, max_level);
		}
	}

	public static Set<Integer> subTreeSets(
			Map<Integer, ArrayList<Integer>> adjacentList, int index,
			boolean[] visited, Map<Integer, Set<Integer>> subTreeMap) {
		visited[index] = true;
		Set<Integer> subTree = new HashSet<Integer>();
		List<Integer> nodes = adjacentList.get(index);
		for (int i = 0; i < nodes.size(); i++) {
			if (!visited[nodes.get(i)]) {
				subTree.add(nodes.get(i));
				Set<Integer> childSubTree = subTreeSets(adjacentList,
						nodes.get(i), visited, subTreeMap);
				if (childSubTree.size() > 0)
					subTree.addAll(childSubTree);
			}
		}
		subTree.add(index);
		subTreeMap.put(index, subTree);
		return subTree;
	}

	public static void bfs(int nodes[], boolean[] visited, int s,
			int[] max_level) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		q.add(-1);
		visited[s] = true;
		int levelNo = 2;
		levelNode.put(1, new ArrayList<Integer>());
		levelNode.get(1).add(s);
		level.put(s, 1);
		parent.put(s, -1);
		while (!q.isEmpty()) {
			int val = (int) (q.poll());
			if (val == -1) {
				levelNo++;
				if (!q.isEmpty())
					q.add(-1);
			} else {
				for (int i : adjacentList.get(val)) {
					if (!visited[i]) {
						visited[i] = true;
						q.add(i);
						if (!levelNode.containsKey(levelNo))
							levelNode.put(levelNo, new ArrayList<Integer>());
						levelNode.get(levelNo).add(i);
						level.put(i, levelNo);
						max_level[0] = Math.max(max_level[0], levelNo);
						parent.put(i, val);
					}
				}
			}
		}
	}

	public static void solve(Scanner in, int q, int[] nodes, int[] max_level) {
		while (q-- > 0) {
			int s = in.nextInt() - 1;
			int l = in.nextInt();
			if (!adjacentList.containsKey(s)) {
				System.out.println(0);
			} else if (max_level[0] < l) {
				System.out.println(0);
			} else if (level.get(s) > l) {
				System.out.println(0);
			} else if (level.get(s) == l) {
				System.out.println(nodes[s]);
			} else {
				long sum = 0;
				if (levelNode.containsKey(l)) {
					for (int i : levelNode.get(l)) {
						boolean check = checkParent(s, i);
						if (check)
							sum += nodes[i];
					}
				}
				System.out.println(sum);
			}
		}
	}

	public static boolean checkParent(int s, int node) {

		return subTreeMap.containsKey(s) && subTreeMap.get(s).contains(node);
	}
}
