package algo.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IterativeTreeTraversals {
	public List<Integer> inorderTraversal_method2(TreeNode root) {

		List<Integer> l = new ArrayList<Integer>();

		if (root == null)
			return l;
		Stack<TreeNode> s = new Stack<TreeNode>();

		while (root != null) {
			s.push(root);
			root = root.left;
		}
		while (!s.isEmpty()) {
			root = s.pop();
			l.add(root.data);
			if (root.right != null) {
				root = root.right;
				while (root != null) {
					s.push(root);
					root = root.left;
				}

			}

		}

		return l;
	}

	public List<Integer> inorderTraversal_method1(TreeNode root) {

		List<Integer> l = new ArrayList<Integer>();
		Stack<TreeNode> s = new Stack<TreeNode>();
		while (true) {
			if (root!= null) {
				s.push(root);
				root = root.left;
			}else{
				if(s.isEmpty())
					break;
				root=s.pop();
				l.add(root.data);
				root=root.right;				
			}

		}

		return l;
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> l = new ArrayList<Integer>();
		if (root == null)
			return l;
		Stack<TreeNode> s = new Stack<TreeNode>();
		s.push(root);
		while (!s.isEmpty()) {
			TreeNode node = s.pop();
			l.add(node.data);

			if (node.right != null)
				s.push(node.right);

			if (node.left != null)
				s.push(node.left);

		}
		return l;
	}
	 public List<Integer> postorderTraversal(TreeNode root) {
	        List<Integer> l=new ArrayList<Integer>();
	        if(root==null)
	            return l;
	        Stack<TreeNode> s1=new Stack<TreeNode>();
	        Stack<TreeNode> s2=new Stack<TreeNode>();
	        s1.push(root);
	        while(!s1.isEmpty())
	        {   root=s1.pop();
	            s2.push(root);
	            if(root.left!=null)
	                s1.push(root.left);
	            if(root.right!=null)
	                s1.push(root.right);
	        }
	        
	        while(!s2.isEmpty())
	            l.add(s2.pop().data );
	            
	        return l;
	        
	    }

}
