package algo.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BSTIterator implements Iterator<Integer> {
    Queue<Integer> q;
    
    private void inorder(TreeNode root,Queue<Integer> q){
        if(root==null)
            return;
        inorder(root.left,q);
        q.add(root.data);
        inorder(root.right,q);
    }
    
    public BSTIterator(TreeNode root) {
        q=new LinkedList<Integer>();
        inorder(root,q);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !q.isEmpty();
    }

    /** @return the next smallest number */
    public Integer next() {
        return q.remove();
    }
}
