package algo.tree;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPaths {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(8);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);
		System.out.println(binaryTreePaths(root));

	}

	public static void binaryTreePaths(TreeNode root, String s, List<String> l) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			s += (root.data);
			l.add(s.toString());
			return;
		}
		s += root.data + "->";
		binaryTreePaths(root.left, s, l);
		binaryTreePaths(root.right, s, l);
		
	}

	public static List<String> binaryTreePaths(TreeNode root) {
		String s ="";
		List<String> l = new ArrayList<String>();
		binaryTreePaths(root, s, l);
		return l;
	}
}