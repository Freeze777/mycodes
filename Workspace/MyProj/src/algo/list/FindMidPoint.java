package algo.list;

public class FindMidPoint {
public static void main(String[] args) {
	ListNode head = new ListNode(1);
	head.next = new ListNode(4);
	head.next.next = new ListNode(9);
	head.next.next.next = new ListNode(4);
	head.next.next.next.next=new ListNode(1);
	ListNode mid=findMidPoint(head);

}

public static ListNode findMidPoint(ListNode head) {
	ListNode slow=head;
	ListNode fast=head;
	
	while(fast!=null && fast.next!=null)
	{
		slow=slow.next;
		fast=fast.next.next;
	}
	return slow;
}
}
