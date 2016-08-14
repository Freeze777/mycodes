package algo.list;

public class RotateALinkedList {

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null)
			return null;
		ListNode t = head;
		int len = 0;
		while (t != null) {
			t = t.next;
			len++;
		}
		k %= len;
		ListNode t2 = head, t1 = head;
		while (k-- > 0)
			t1 = t1.next;
		while (t1.next != null)// t2 points to (k+1)th from the end
		{
			t1 = t1.next;
			t2 = t2.next;
		}
		t1.next = head;
		head = t2.next;
		t2.next = null;

		return head;

	}

}
