package algo.list;

import algo.trees.TreeNode;

public class ListUtility {
	public static void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.data + " -> ");
			head = head.next;
		}
		System.out.print(null + "\n");
	}

	public static void printList(TreeNode head) {
		while (head != null) {
			System.out.print(head.data + " -> ");
			head = head.right;
		}
		System.out.print(null + "\n");
	}

	public static void printList(DLLNode head) {
		while (head != null) {
			System.out.print(head.data + " -> ");
			head = head.next;
		}
		System.out.print(null + "\n");
	}
}
