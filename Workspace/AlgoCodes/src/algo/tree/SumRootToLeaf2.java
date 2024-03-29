package algo.tree;


public class SumRootToLeaf2 {
    public void sumNumbers(TreeNode root,int num,int[] sum) {
        if(root==null)
            return;
        num=num*10+root.data;
        if(root.left==null && root.right==null)
            sum[0]+=num;
        sumNumbers(root.left,num,sum);
        sumNumbers(root.right,num,sum);
    }
    public int sumNumbers(TreeNode root) {
        int[] sum={0};
        sumNumbers(root,0,sum);
        return sum[0];
    }
}