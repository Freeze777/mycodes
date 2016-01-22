package algo.trees;

public class TreeUtility {
	
	public static boolean isLeaf(TreeNode root) {
		return ((root.left==null)&&(root.right==null));
	}

	public static void printIOT(TreeNode root) {
		if (root == null)
			return;
		printIOT(root.left);
		System.out.print(root.data + " ");
		printIOT(root.right);
	}
	public static void printPoOT(TreeNode root) {
		if (root == null)
			return;
		printIOT(root.left);
		printIOT(root.right);
		System.out.print(root.data + " ");
	}
	public static void printPreOT(TreeNode root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		printIOT(root.left);
		printIOT(root.right);
	}
	public static int height(TreeNode root) {
		if(root==null)return 0;
		return (1+Math.max(height(root.left),height(root.right)));
	}
}
