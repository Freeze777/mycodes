package algo.list;

public class MergeSortOnList {
	public static ListNode findMidPoint(ListNode head) {
		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		//This was added as part of bug.For list size of 2 midpoint is the last element
		if (head.next.next == null) {
			if (head.data > head.next.data) {
				ListNode t = head.next;
				head.next = null;
				t.next = head;
				head = t;
			}
			return head;
		}
		ListNode mid = findMidPoint(head);
		ListNode left = head;
		ListNode right = mid.next;
		mid.next = null;
		left=sortList(left);
		right=sortList(right);
		ListNode hd = mergeList(left, right);
		return hd;
	}

	public static ListNode mergeList(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		ListNode head = null, t = null;
		// for setting up the head pointer to the list
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
		// iterate through both the list
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
			t = t.next;// missed this statement and got fucked up..!!silly!!
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		head.next = new ListNode(1);
		head.next.next = new ListNode(8);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(2);

		ListUtility.printList(sortList(head));
	}
}