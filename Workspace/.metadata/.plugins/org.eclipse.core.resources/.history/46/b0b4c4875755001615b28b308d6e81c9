 package algo.trees;

public class NthLargestandSmallestElementInBST {
	

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);

		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.left.right.right = new TreeNode(4);

		root.right = new TreeNode(10);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(20);
		root.right.left.left = new TreeNode(6);
		root.right.left.right = new TreeNode(8);

		TreeUtility.printIOT(root);
		System.out.println();
		int[] n = { 1 };
		findNthLargest(root, n);
		
		n[0]=1;
		findNthSmallest(root, n);
	}

	private static void findNthLargest(TreeNode root, int[] n) {
		if (root == null)
			return;
		findNthLargest(root.right, n);
		n[0]--;
		if (n[0] == 0)
			System.out.println(root.data);
		findNthLargest(root.left, n);
	}

	private static void findNthSmallest(TreeNode root, int[] n) {
		if (root == null)
			return;
		findNthSmallest(root.left, n);
		n[0]--;
		if (n[0] == 0)
			System.out.println(root.data);
		findNthSmallest(root.right, n);
	}
	 public void kthSmallest(TreeNode root, int[] k_ans) {
	        if(root==null)
	            return;
	        kthSmallest(root.left,k_ans);
	        k_ans[0]--;
	        if(k_ans[0]==0)
	            k_ans[1]=root.data;
	        kthSmallest(root.right,k_ans);
	    }
	    public int kthSmallest(TreeNode root, int k) {
	        
	        int[] k_ans={k,-1};
	        kthSmallest(root,k_ans);
	        
	        return k_ans[1];
	        
	    }
}
