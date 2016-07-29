package algo.tree;

import java.util.Map;
import java.util.TreeMap;

public class BottomView {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);

		root.left = new TreeNode(2);
		root.left.left = new TreeNode(12);
		root.left.right = new TreeNode(3);
		root.left.left.right = new TreeNode(10);

		root.right = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		root.right.right.left = new TreeNode(11);
		root.right.right.right = new TreeNode(8);

		// TreeUtility.printIOT(root);
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		printBottomView(root, 0, map);
		System.out.println("\n" + map.values());

	}

	public static void printBottomView(TreeNode root, int verticalLevel,
			Map<Integer, Integer> map) {
		if (root == null)
			return;
		map.put(verticalLevel, root.data);
		printBottomView(root.left, verticalLevel - 1, map);
		printBottomView(root.right, verticalLevel + 1, map);
	}
}
