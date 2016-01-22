package algo.trees;

import algo.list.ListUtility;

public class BST2DLL {
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

		DoublePointer<TreeNode> prev = new DoublePointer<TreeNode>(null);
		DoublePointer<TreeNode> head = new DoublePointer<TreeNode>(null);

		bst2DLL(root, head, prev);
		System.out.println();
		ListUtility.printList(head.getValue());

	}

	public static void bst2DLL(TreeNode root, DoublePointer<TreeNode> head,
			DoublePointer<TreeNode> prev) {
		if (root == null)
			return;
		
		bst2DLL(root.left, head, prev);
		
		if (prev.getValue() != null) {
			root.left = prev.getValue();
			prev.getValue().right = root;
		} else {
			head.setValue(root);
		}
		prev.setValue(root);

		bst2DLL(root.right, head, prev);
	}
}
