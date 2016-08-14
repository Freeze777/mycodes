package algo.tree;

import java.util.Stack;

public class FlattenTreeToPreOrderLinkedList {
    public TreeNode flatten(TreeNode root) {
        if(root==null)
            return null;
        Stack<TreeNode> s=new Stack<TreeNode>();
        s.add(root);
        while(!s.isEmpty()){
            root=s.pop();
            if(root.right!=null)
                s.add(root.right);
            if(root.left!=null)
                s.add(root.left);
            if(!s.isEmpty())
                root.right=s.peek();
            else
                root.right=null;
            root.left=null;
        }
        return root;
    }
}
