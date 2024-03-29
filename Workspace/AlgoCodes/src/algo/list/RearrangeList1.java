package algo.list;

/*Given a linked list eg : 1->2->3->4->5->6, make the following changes 1->4->2->5->3->6:*/
public class RearrangeList1 {
	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(5);
		head.next.next = new ListNode(7);
		head.next.next.next = new ListNode(8);
		head.next.next.next.next = new ListNode(15);
		head.next.next.next.next.next = new ListNode(25);
		head = rearrange(head);
		ListUtility.printList(head);
	}

	public static ListNode rearrange(ListNode head) {
		ListNode mid = FindMidPoint.findMidPoint(head);
		ListNode crawl1 = head, crawl2 = mid, t1 = null, t2 = null;
		while (crawl1.next != mid) {
			t1 = crawl1.next;
			crawl1.next = crawl2;
			t2 = crawl2.next;
			crawl2.next = t1;
			crawl1 = t1;
			crawl2 = t2;
		}
		crawl1.next = crawl2;
		return head;
	}
}
