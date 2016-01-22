package algo.trees;

public class SumOfLeftLeaves {
	static int  sum=0;
	static TreeNode  prev=null;
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
		findSum(root);
		System.out.println(sum);

	}
	private static void findSum(TreeNode root) {
		if(root==null)
			return;
		findSum(root.left);
		if((prev!=null)&&(root.left==prev)&& (TreeUtility.isLeaf(prev)))
			sum+=prev.data;
		
		prev=root;
		
		findSum(root.right);
			
	}

}
