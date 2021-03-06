package algo.tree;


public class BuildTree {

	public static void main(String[] args) {
		int[] inorder = { 4, 2, 1, 5, 6, 3 };
		int[] levelorder = { 1, 2, 3, 4, 5, 6 };
		//DoublePointer<Integer> current = new DoublePointer<Integer>(0);
		int rootIndex = search(inorder, 0, inorder.length - 1, levelorder[0]);
		//TreeNode root = buildTreeFromLevelOrder(inorder, 0, inorder.length - 1,
		//		levelorder, rootIndex, current);
		//TreeUtility.printIOT(root);
	}

	
/*
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
*/
	private static TreeNode buildTreeFromPreorder(int[] inorder,int[] preorder,int preStart,int preEnd,int inStart,
			int inEnd) {
		if(inStart>inEnd||preStart>preEnd)
			return null;
		TreeNode root=new TreeNode(preorder[preStart]);
		int i=search(inorder, inStart, inEnd, root.data);
		int leftTotal=i-inStart;
		int rightTotal=inEnd-i;
		root.left=buildTreeFromPreorder(inorder,preorder,preStart+1,preStart+leftTotal,inStart,i-1);
        root.right=buildTreeFromPreorder(inorder,preorder,preEnd-rightTotal+1,preEnd,i+1,inEnd);
		return root;
	}

	public static int search(int[] A, int start, int end, int key) {
		for (int i = start; i <= end; i++) {
			if (A[i] == key)
				return i;
		}

		return -1;
	}
	
	 public TreeNode buildTreeHelper_PostOrder(int[] inorder, int[] postorder,
				int postStart, int postEnd, int inStart, int inEnd) {

			if (inStart > inEnd||postStart>postEnd)
				return null;

			int rootVal = postorder[postEnd];
			TreeNode root = new TreeNode(rootVal);
			int i=search(inorder, inStart, inEnd, root.data);
			int leftTotal = i - inStart;
			int rightTotal = inEnd - i;
				
			root.left = buildTreeHelper_PostOrder(inorder, postorder, postStart, postStart
					+ leftTotal - 1, inStart, i - 1);
			root.right = buildTreeHelper_PostOrder(inorder, postorder, postEnd - rightTotal,
					postEnd - 1, i + 1, inEnd);

			return root;

		}

		public TreeNode buildTree_PostOrder(int[] inorder, int[] postorder) {
			int[] postIndex = { 0 };
			return buildTreeHelper_PostOrder(inorder, postorder,0, postorder.length-1, 0,
					inorder.length - 1);

		}

}
