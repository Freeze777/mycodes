package algo.tree;

public class PrintNodesAtDistanceK {
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

		int k = 3;
		printNodesAtkDistance(root, 7, k);
	}

	public static int printNodesAtkDistance(TreeNode root, int target, int k) {
		if (root == null)
			return -1;
		if (root.data == target) {
			printNodesAtkDistanceDown(root, k);
			return 0;
		}

		int left = printNodesAtkDistance(root.left, target, k);
		if (left != -1) {
			if (left + 1 == k)
				System.out.print(root.data + " ");
			else
				printNodesAtkDistanceDown(root.right, k - left - 2);
			return left + 1;
		}
		int right = printNodesAtkDistance(root.right, target, k);
		if (right != -1) {
			if (right + 1 == k)
				System.out.print(root.data + " ");
			else
				printNodesAtkDistanceDown(root.left, k - right - 2);

			return right + 1;
		}
		return -1;
	}

	public static void printNodesAtkDistanceDown(TreeNode root, int k) {
		if (root == null)
			return;

		if (k == 0) {
			System.out.print(root.data + " ");
			return;
		}
		printNodesAtkDistanceDown(root.left, k - 1);
		printNodesAtkDistanceDown(root.right, k - 1);
	}
}
