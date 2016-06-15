package algo.list;

public class ReverseAlinkedList {
	static ListNode hd;

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(5);
		ListUtility.printList(head);

		head = reverseListRecursion(head);
		ListUtility.printList(head);

	}

	public static ListNode reverseListRecursion(ListNode head) {
		// check for empty list(100% no chance of occurence(even during the
		// recursion) unless we deliberelatly call it with null)
		// check for list with single node(happen when the recursion reaches the
		// end of the list)
		// reverse of single node list is the list itself;
		if (head == null || head.next == null)
			return head;
		// the reverse gets it value when the recursion reaches the end of list
		// no modifications are made afterwards and its is return as such;
		// rescurse on the remaing part of the list
		ListNode reverse = reverseListRecursion(head.next);
		// linking the next node of head to itself
		// at this point head points to the node which is to made the last node
		// of the currently reversed list
		head.next.next = head;
		head.next = null;
		// head point to last node of the reversed list now
		return reverse;
	}

	public static ListNode reverseList(ListNode head) {
		ListNode previous = null;
		ListNode current = head;
		ListNode next = null;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
}
