package algo.trees;
/*
 * remove TreeNodes in  root to leaf path which doesnt have sum >= k 
 * 
 * */
public class PruneTree {

	public static void main(String[] args) {
		int sum=20;
		
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
		root=pruneTree(root,sum);
		TreeUtility.printIOT(root);
	}

	private static TreeNode pruneTree(TreeNode root, int sum) {
		if(root==null)
			return root;
		root.left=pruneTree(root.left, sum-root.data);
		root.right=pruneTree(root.right, sum-root.data);
		
		if(TreeUtility.isLeaf(root)&&(sum>root.data))
		return null;
		
		
		return root;
	}

	
}
