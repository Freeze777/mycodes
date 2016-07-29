package algo.tree;

public class MirrorTree {
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
		toMirrorTree(root);
		TreeUtility.printIOT(root);
	}

	public boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null)
			return root1 == root2;
		return ((root1.data == root2.data) && isMirror(root1.left, root2.right) && isMirror(
				root1.right, root2.left));
	}

	public boolean isMirror(TreeNode root) {
		return isMirror(root, root);
	}

	private static void toMirrorTree(TreeNode root) {
		if (root == null)
			return;

		toMirrorTree(root.left);
		toMirrorTree(root.right);

		TreeNode temp = root.right;
		root.right = root.left;
		root.left = temp;
	}
}
