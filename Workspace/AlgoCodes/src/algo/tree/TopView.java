package algo.tree;

import java.util.*;
/*
 * 
 *    1
     / \
    2   3
   / \
  4   5
       \
        6
         \
          7
 My first logic: keep going left print them in reverse..and keep going right.. print them..
 Clearly wrong because 7 wont be printed.
 Second Logic: Print first element who are first seen in each vertical level
 Clearly wrong because 6 and 3 are in same vertical level.but 6 will be chosen because its visited first.
 
 
 Correct Logic: Use level traversal.. keep track of the vertical level of nodes... 
 A node which is first in the vertical level as we traverse horizontal level by level is the answer.
 * */
class QNode {
	TreeNode tr;
	int vLevel;

	public QNode(TreeNode tr, int vLevel) {
		this.tr = tr;
		this.vLevel = vLevel;
	}

}

public class TopView {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);
		TreeUtility.printLOT(root);
		//System.out.println(printTopView(root));
	}

	private static Map<Integer, Integer> printTopView(TreeNode root) {
		Queue<QNode> q = new LinkedList<QNode>();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		StringBuilder ans=new StringBuilder();
		q.add(new QNode(root, 0));
		while (!q.isEmpty()) {
			QNode t = q.remove();
			int currVlevel = t.vLevel;
			if (!map.containsKey(currVlevel)) {
				map.put(currVlevel, t.tr.data);
				if(currVlevel>=0)
					ans.append(t.tr.data+" ");
				else
					ans.insert(0, t.tr.data+" ");
			}
			if (t.tr.left != null) {
				QNode left = new QNode(t.tr.left, t.vLevel - 1);
				q.add(left);
			}
			if (t.tr.right != null) {
				QNode right = new QNode(t.tr.right, t.vLevel + 1);
				q.add(right);
			}

		}
		System.out.println(ans);
		return map;
	}
}
