package algo.trees;

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
		printLeftView(root, 0, maxLevel);
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

	private static void printLeftView_levelOrder(TreeNode root) {
		Queue<TreeNode> Q = new LinkedList<TreeNode>();
		Q.add(root);
		Q.add(null);
		System.out.println(root.data);
		while (!Q.isEmpty()) {
			TreeNode current = Q.remove();
			if (current == null) {
				if (Q.isEmpty()) {
					break;
				}
				if(Q.peek()!=null)
				System.out.println(Q.peek().data);
				else
				Q.add(null);
			} else {
				Q.add(current.right);
				Q.add(current.left);
			
			}
		}
	}
}
