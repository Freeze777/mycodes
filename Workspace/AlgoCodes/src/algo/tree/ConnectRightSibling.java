package algo.tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}

public class ConnectRightSibling {

	public void connect(TreeLinkNode root) {
		//Couldnt figure out this corner case,so got timed out
		if (root == null)
			return;

		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
		q.add(root);
		q.add(null);
		while (true) {
			TreeLinkNode node = q.remove();
			if (node != null) {
				node.next = q.peek();
				if (node.left != null)
					q.add(node.left);
				if (node.right != null)
					q.add(node.right);
			} else {
				if (q.isEmpty())
					break;
				q.add(null);
			}

		}

	}
}
