package algo.list;

public class ReverseEverykNodes {
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next=new ListNode(2);
		head.next.next.next.next.next=new ListNode(8);
		ListUtility.printList(head);
		int k=3;
		head=reverseEverykNodes(head,k);
		ListUtility.printList(head);
	}

	public static ListNode reverseEverykNodes(ListNode head, int k) {
		if (head==null)
			return head;
		ListNode current=head,prev=null,next=null;
		int count=0;
		while(current!=null&&count<k)
		{
			next=current.next;
			current.next=prev;
			prev=current;
			current=next;
			count++;
		}
		//at the end of the each iteration current and next points to head node of unreversed list
		//and prev points to the head of the reversed list
		
	//Note: head remains unchanged, it points to the actual head of the original list 
		head.next=reverseEverykNodes(next, k);
		//prev points to the head the partially reversed list
		return prev;
	}

}
