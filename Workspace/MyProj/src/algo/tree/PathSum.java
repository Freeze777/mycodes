package algo.tree;


public class PathSum {
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root==null)
            return false;
        sum-=root.data;
        if(root.left==null && root.right==null)
            return sum==0;
        return hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }
    public static void main(String[] args) {
		
	}
}