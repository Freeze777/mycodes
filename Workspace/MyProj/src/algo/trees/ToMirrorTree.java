package algo.trees;

public class ToMirrorTree {
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
