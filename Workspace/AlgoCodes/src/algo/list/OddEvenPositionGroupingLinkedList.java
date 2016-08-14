package algo.list;

import algo.list.ListNode;

public class OddEvenPositionGroupingLinkedList {

	public ListNode oddEvenList(ListNode head) {
		if (head == null)
			return null;
		ListNode t1 = head;
		ListNode t2 = t1.next;
		ListNode headOdd = t1, headEven = t2;
		// divide the list into two list.. a list of odd positoned and even
		// positioned
		// always t2 is ahead of t1 : we need to check whether its null or
		// t2.next is null
		while (t2 != null) {
			t1.next = t2.next;
			if (t2.next != null) {
				t2.next = t2.next.next;
				t1 = t1.next;
				t2 = t1.next;
			} else {
				break;// for an even length list this happens..t2 points to last node
			}
		}
		t1.next = headEven;
		return headOdd;
	}

}
