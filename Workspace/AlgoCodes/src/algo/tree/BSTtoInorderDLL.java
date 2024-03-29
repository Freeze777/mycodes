package algo.tree;

import algo.list.ListUtility;

public class BSTtoInorderDLL {
	/*			15
	 *		  /    \
	 *		 7      24
	 *
	 * 
	 * */
	public static void main(String[] args) {
		TreeNode root = new TreeNode(15);

		root.left = new TreeNode(7);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(10);
		root.left.left.left = new TreeNode(1);
		root.left.left.right = new TreeNode(5);

		root.right = new TreeNode(24);
		root.right.left = new TreeNode(19);
		root.right.right = new TreeNode(30);
		root.right.left.left = new TreeNode(16);
		root.right.left.right = new TreeNode(21);
		TreeUtility.printIOT(root);
		TreeNode[] head={null},prev={null};
		bst2DLL(root, head, prev);
		System.out.println();
		ListUtility.printList(head[0]);

	}

	public static void bst2DLL(TreeNode root, TreeNode[] head,
			TreeNode[] prev) {
		if (root == null)
			return;
		bst2DLL(root.left, head, prev);
		if (prev[0] != null) {
			root.left = prev[0];
			prev[0].right = root;
		} else {
			head[0]=root;
		}
		prev[0]=root;
		bst2DLL(root.right, head, prev);
		
	}
}
