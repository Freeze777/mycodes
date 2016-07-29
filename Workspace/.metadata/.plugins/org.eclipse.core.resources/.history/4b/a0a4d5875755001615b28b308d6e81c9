package algo.trees;

import java.util.Stack;

public class ZigZagTraversal {

	public static void main(String[] args) {

		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(10);
		root.left.left = new TreeNode(9);
		root.left.right = new TreeNode(17);
		root.left.left.left = new TreeNode(77);
		root.left.left.right = new TreeNode(2);

		root.right = new TreeNode(7);
		root.right.left = new TreeNode(8);
		root.right.right = new TreeNode(1);
		root.right.left.left = new TreeNode(3);
		root.right.left.right = new TreeNode(-10);

		zigZagTraversal(root);
	}

	public static void zigZagTraversal(TreeNode root) {
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		boolean dir = false;
		s2.push(root);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			TreeNode temp=null;
			if (dir) {
				while (!s1.isEmpty()) {
					temp = s1.pop();
					if (temp != null)
						System.out.print(temp.data + " ");
					if (temp.left != null)
						s2.push(temp.left);

					if (temp.right != null)
						s2.push(temp.right);
				}
			} else {
				while (!s2.isEmpty()) {
					temp = s2.pop();
					if (temp != null)
						System.out.print(temp.data + " ");
					if (temp.right != null)
						s1.push(temp.right);
					
					if (temp.left != null)
						s1.push(temp.left);

				}
			}
		

			dir = !dir;

		}
	}

}
