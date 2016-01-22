package algo.trees;

public class LowestCommonAncestor {
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

		TreeNode lca = LCA(root, 3, 1);
		System.out.println(lca.data);
	}

	private static TreeNode LCA(TreeNode root, int n1, int n2) {
		if (root == null || root.data == n1 || root.data == n2)
			return root;

		TreeNode leftLCA = LCA(root.left, n1, n2);
		TreeNode rightLCA = LCA(root.right, n1, n2);

		if (leftLCA != null && rightLCA != null)
			return root;
		else if (leftLCA != null)
			return leftLCA;
		else
			return rightLCA;
	}
}
