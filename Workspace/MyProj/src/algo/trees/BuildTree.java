package algo.trees;

import java.util.Arrays;

public class BuildTree {

	public static void main(String[] args) {
		int[] inorder = { 4, 2, 1, 5, 6, 3 };
		int[] levelorder = { 1, 2, 3, 4, 5, 6 };
		DoublePointer<Integer> current = new DoublePointer<Integer>(0);
		int rootIndex = search(inorder, 0, inorder.length - 1, levelorder[0]);
		TreeNode root = buildTreeFromLevelOrder(inorder, 0, inorder.length - 1,
				levelorder, rootIndex, current);
TreeUtility.printIOT(root);
	}

	private static TreeNode buildTreeFromLevelOrder(int[] inorder, int start,
			int end, int[] levelorder, int rootIndex,
			DoublePointer<Integer> current) {
		if (start > end)
			return null;
		int pos = current.getValue();
		if (start == end) {
			current.setValue(pos + 1);
			return new TreeNode(levelorder[pos]);
		}

		TreeNode root = new TreeNode(levelorder[pos]);
		current.setValue(++pos);
		int nextIndex = search(inorder, start, end, levelorder[pos]);
		if (nextIndex < rootIndex)
			root.left = buildTreeFromLevelOrder(inorder, start, rootIndex - 1,
					levelorder, nextIndex, current);
		else
			root.right = buildTreeFromLevelOrder(inorder, rootIndex + 1, end,
					levelorder, nextIndex, current);
		return root;
	}

	private static TreeNode buildTreeFromPreorder(int[] inorder, int start,
			int end, int[] preorder, DoublePointer<Integer> current) {

		if (start > end)
			return null;

		int pos = current.getValue();
		if (start == end) {
			current.setValue(pos + 1);
			return new TreeNode(preorder[pos]);
		}
		TreeNode root = new TreeNode(preorder[pos]);
		int index = search(inorder, start, end, root.data);
		current.setValue(pos + 1);
		root.left = buildTreeFromPreorder(inorder, start, index - 1, preorder,
				current);
		root.right = buildTreeFromPreorder(inorder, index + 1, end, preorder,
				current);

		return root;
	}

	public static int search(int[] A, int start, int end, int key) {
		for (int i = start; i <= end; i++) {
			if (A[i] == key)
				return i;
		}

		return -1;
	}

}
