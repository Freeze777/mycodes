package algo.tree;

public class CheckATreeIsBST {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(3);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(4);
		// root.left.left.left = new TreeNode(1);
		// root.left.left.right = new TreeNode(2);

		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		// root.right.left.left = new TreeNode(3);
		// root.right.left.right = new TreeNode(-10);
		TreeNode[] prev = { new TreeNode(Integer.MIN_VALUE) };
		boolean flag = checkBTisBST_IOT(root, prev);
		//boolean flag = checkBTisBST_MinMax(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
		System.out.println(flag);
	}

	private static boolean checkBTisBST_MinMax(TreeNode root, int min, int max) {
		if (root == null)
			return true;

		return ((root.data >= min && root.data < max) // root.data >= min to
														// allow duplicates
				&& checkBTisBST_MinMax(root.left, min, root.data) && checkBTisBST_MinMax(
					root.right, root.data, max));
	}

	public static boolean checkBTisBST_IOT(TreeNode root, TreeNode[] prev) {
		if (root == null)
			return true;

		boolean left = checkBTisBST_IOT(root.left, prev);

		left = left && (prev[0].data < root.data);
		prev[0] = root;
		boolean right = checkBTisBST_IOT(root.right, prev);

		return (left && right);
	}
}
