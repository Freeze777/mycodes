package algo.tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtility {

	public static boolean isLeaf(TreeNode root) {
		return ((root.left == null) && (root.right == null));
	}

	public static void printIOT(TreeNode root) {
		if (root == null)
			return;
		printIOT(root.left);
		System.out.print(root.data + " ");
		printIOT(root.right);
	}

	public static void printPoOT(TreeNode root) {
		if (root == null)
			return;
		printIOT(root.left);
		printIOT(root.right);
		System.out.print(root.data + " ");
	}

	public static void printPreOT(TreeNode root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		printIOT(root.left);
		printIOT(root.right);
	}

	public static int height(TreeNode root) {
		if (root == null)
			return 0;
		return (1 + Math.max(height(root.left), height(root.right)));
	}

	public static void printLOT(TreeNode root) {
		Queue<TreeNode> Q = new LinkedList<TreeNode>();
		Q.add(root);
		Q.add(null);
		int level = 0;
		System.out.print(level + ": ");
		while (!Q.isEmpty()) {
			TreeNode current = Q.remove();
			if (current != null) {
				System.out.print(current.data + " ");

				if (current.left != null)
					Q.add(current.left);
				if (current.right != null)
					Q.add(current.right);

			} else {

				if (Q.isEmpty()) {
					System.out.println();
					break;
				}
				Q.add(null);
				System.out.print("\n" + (++level) + ": ");

			}
		}

	}
}
