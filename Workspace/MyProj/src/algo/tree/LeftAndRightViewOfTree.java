package algo.tree;

import java.util.LinkedList;
import java.util.Queue;

public class LeftAndRightViewOfTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(10);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(17);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(2);

		root.right = new TreeNode(7);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(1);
		root.right.left.left = new TreeNode(3);
		root.right.left.right = new TreeNode(-10);

		TreeUtility.printIOT(root);
		System.out.println();
		DoublePointer<Integer> maxLevel = new DoublePointer<Integer>(
				Integer.MIN_VALUE);
		// printLeftView(root, 0, maxLevel);
		printLeftAndRightView_levelOrder(root);
		// TreeUtility.printIOT(root);
	}

	private static void printLeftView(TreeNode root, int level,
			DoublePointer<Integer> maxLevel) {
		if (root == null)
			return;

		if (maxLevel.getValue() < level) {
			maxLevel.setValue(level);
			System.out.println(root.data);
		}
		printLeftView(root.left, level + 1, maxLevel);

		printLeftView(root.right, level + 1, maxLevel);

	}

	private static void printRightView(TreeNode root, int level,
			DoublePointer<Integer> maxLevel) {
		if (root == null)
			return;
		if (maxLevel.getValue() < level) {
			maxLevel.setValue(level);
			System.out.println(root.data);
		}
		printRightView(root.right, level + 1, maxLevel);
		printRightView(root.left, level + 1, maxLevel);
	}

	private static void printLeftAndRightView_levelOrder(TreeNode root) {
		StringBuilder leftView = new StringBuilder();
		StringBuilder rightView = new StringBuilder();
		Queue<TreeNode> Q = new LinkedList<TreeNode>();
		Q.add(root);
		Q.add(null);
		while (!Q.isEmpty()) {
			TreeNode current = Q.remove();
			if (current != null) {
				if (Q.peek() == null)
					rightView.append(current.data + " ");
				if (current.left != null)
					Q.add(current.left);
				if (current.right != null)
					Q.add(current.right);

			} else {

				if (Q.isEmpty()) {
					System.out.println();
					break;
				}
				leftView.append(Q.peek().data + " ");
				Q.add(null);

			}
		}
		System.out.println("Left-View: " + leftView);
		System.out.println("Right-View: " + rightView);
	}
}
