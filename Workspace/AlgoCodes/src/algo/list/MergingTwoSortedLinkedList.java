package algo.list;

public class MergingTwoSortedLinkedList {
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		l1.next.next.next = new ListNode(9);

		ListNode l2 = new ListNode(0);
		l2.next = new ListNode(5);
		l2.next.next = new ListNode(7);
		l2.next.next.next = new ListNode(8);
		l2.next.next.next.next = new ListNode(15);
		
		ListUtility.printList(mergeTwoLists(l1,l2));
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		ListNode head = null, t = null;
		//for setting up the head pointer to the list
		if (l1 != null && l2 != null) {
			if (l2.data > l1.data) {
				head = l1;
				t = head;
				l1 = l1.next;
			} else {
				head = l2;
				t = head;
				l2 = l2.next;
			}
		} else if (l1 != null) {
			head = l1;
			t = head;
			l1 = l1.next;
		} else {
			head = l2;
			t = head;
			l2 = l2.next;
		}
		//iterate through both the list
		while (l1 != null || l2 != null) {
			if (l2 == null && l1 != null) {
				t.next = l1;
				l1 = l1.next;
			} else if (l1 == null && l2 != null) {
				t.next = l2;
				l2 = l2.next;
			} else {
				if (l2.data > l1.data) {
					t.next = l1;
					l1 = l1.next;
				} else {
					t.next = l2;
					l2 = l2.next;
				}
			}
			t=t.next;//missed this statement and got fucked up..!!silly!!
		}
		return head;
	}
}