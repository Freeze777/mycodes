package algo.trees;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import algo.list.DLLNode;
import algo.list.ListUtility;

public class VerticalSum {
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

		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		DLLNode head = new DLLNode(0);
		 findVerticalSumHashMap(root, 0, map);
		findVerticalSumDLL(root,0, head);
		while(head.previous!=null)head=head.previous; 
		ListUtility.printList(head);
		System.out.println(map.values());
	}

	public static void findVerticalSumDLL(TreeNode root, int direction,
			DLLNode curr) {
		if (root == null)
			return;

		if (direction == 1) {
			if (curr.next == null) {
				DLLNode temp = new DLLNode(root.data);
				curr.next = temp;
				temp.previous = curr;
				curr = temp;
			} else {
				curr = curr.next;
				curr.data += root.data;
			}

		} else if (direction == -1) {
			
			if (curr.previous == null) {
				DLLNode temp = new DLLNode(root.data);
				curr.previous = temp;
				temp.next = curr;
				curr = temp;
			} else {
				curr = curr.previous;
				curr.data += root.data;
			}

		}else{
			curr.data+=root.data;
		}
	
		findVerticalSumDLL(root.left,-1,curr);
		findVerticalSumDLL(root.right,1, curr);
		
	}

	public static void findVerticalSumHashMap(TreeNode root, int verticalIndex,
			Map<Integer, Integer> map) {
		if (root == null)
			return;
		int prevSum = (map.get(verticalIndex) == null) ? 0 : (map
				.get(verticalIndex));
		map.put(verticalIndex, root.data + prevSum);
		findVerticalSumHashMap(root.left, verticalIndex - 1, map);
		findVerticalSumHashMap(root.right, verticalIndex + 1, map);

	}
}
