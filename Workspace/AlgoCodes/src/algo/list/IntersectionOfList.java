package algo.list;

public class IntersectionOfList {

	public static int findLength(ListNode head) {
		int len = 0;
		while (head != null) {
			head = head.next;
			len++;
		}
		return len;
	}

	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
			return null;
		int la = findLength(headA);
		int lb = findLength(headB);
		int diff = Math.abs(la - lb);
		if (la > lb) {
			while (diff-- > 0)
				headA = headA.next;
		} else {
			while (diff-- > 0)
				headB = headB.next;
		}
		while (headB != null && headA != null) {
			if (headA == headB)//moved up to handle corner case [1->null] and [1->null]
				break;
			headB = headB.next;
			headA = headA.next;
		}

		return headA == headB ? headA : null;
	}

}
