package algo.tree;

public class DiameterOfATree {
	// static int diameter=0;
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(10);
		root.left.left = new TreeNode(1);
		// root.left.right = new TreeNode(17);
		root.left.left.left = new TreeNode(1);
		// root.left.left.right = new TreeNode(2);
		/*
		 * root.right = new TreeNode(7); root.right.left = new TreeNode(8);
		 * root.right.right = new TreeNode(1); root.right.left.left = new
		 * TreeNode(3); root.right.left.right = new TreeNode(-10);
		 */
		int[] dia = { 0 };
		System.out.println(modifiedHeight(root, dia));
		System.out.println(dia[0]);
	}

	// O(n^2) algorithm
	public static int diameter(TreeNode root) {
		if (root == null)
			return 0;

		int leftHeight = TreeUtility.height(root.left);
		int rightHeight = TreeUtility.height(root.right);

		return (Math.max(1 + leftHeight + rightHeight,
				Math.max(diameter(root.right), diameter(root.left))));
	}

	// O(n) algorithm
	public static int modifiedHeight(TreeNode root, int[] dia) {
		if (root == null)
			return 0;

		int leftHeight = modifiedHeight(root.left, dia);
		int rightHeight = modifiedHeight(root.right, dia);
		int d = leftHeight + rightHeight + 1;
		if (d > dia[0])
			dia[0] = d;

		return (1 + Math.max(leftHeight, rightHeight));
	}

}
