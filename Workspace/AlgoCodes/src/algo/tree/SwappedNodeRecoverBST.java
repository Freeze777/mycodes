package algo.tree;


public class SwappedNodeRecoverBST {
    
    public void inorder(TreeNode root,TreeNode[] nodes,TreeNode[] prev) {
        if(root==null)
            return;
        inorder(root.left,nodes,prev);
        if(prev[0].data>root.data){
            if(nodes[0]==null){
                nodes[0]=prev[0];
                nodes[1]=root;
            }
            else
                nodes[1]=root;
        }
        prev[0]=root;
        inorder(root.right,nodes,prev);
    }
    
    public void recoverTree(TreeNode root) {
        TreeNode[] nodes={null,null};
        TreeNode[] prev={new TreeNode(Integer.MIN_VALUE)};
        inorder(root,nodes,prev);
        int t=nodes[0].data;
        nodes[0].data=nodes[1].data;
        nodes[1].data=t;
    }
}