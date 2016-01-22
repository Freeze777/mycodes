package algo.list;

public class CheckForPalidrome {
	public static void main(String[] args) {
		ListNode head = new ListNode(6);
		head.next = new ListNode(6);
		head.next.next = new ListNode(8);
		head.next.next.next = new ListNode(6);
		head.next.next.next.next = new ListNode(6);
		ListUtility.printList(head);
		System.out.println(checkForPalindrome(head));
	}

	private static boolean checkForPalindrome(ListNode head) {
		ListNode mid = FindMidPoint.findMidPoint(head);
		ListNode revHead = ReverseAlinkedList.reverseListRecursion(mid);
		ListNode temp=revHead;
		boolean flag=true;
		while(temp!=null)
		{
			flag&=(temp.data==head.data);
			temp=temp.next;
			head=head.next;
		}
		ReverseAlinkedList.reverseListRecursion(revHead);
		return flag;
	}
}
