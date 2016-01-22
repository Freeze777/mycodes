package algo.list;

public class ReverseAlternatekNodes {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		ListUtility.printList(head);
		int k = 2;
		head = reverseAlternatekNodes(head, k);
		ListUtility.printList(head);
	}

	public static ListNode reverseAlternatekNodes(ListNode head, int k) {
		if (head == null)
			return head;
		ListNode current = head, prev = null, next = null;
		int count = 0;
		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
		head.next = next;
		count = 0;
		while (current != null && count < k - 1) {
			current = current.next;
			count++;
		}
		if (current != null)
			current.next = reverseAlternatekNodes(current.next, k);

		return prev;
	}

}
