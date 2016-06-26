package algo.list;

public class CheckForPalidrome {
	public static void main(String[] args) {
		ListNode head = new ListNode(6);
		head.next = new ListNode(6);
		head.next.next = new ListNode(8);
		head.next.next.next = new ListNode(6);
		head.next.next.next.next = new ListNode(6);
		ListUtility.printList(head);
		//System.out.println(checkForPalindrome(head));
	}

    public ListNode findMidPoint(ListNode head){
        ListNode slow=head,fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public ListNode reverse(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode revHead=reverse(head.next);
        head.next.next=head;
        head.next=null;
        return revHead;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode mid=findMidPoint(head);
        ListNode revHead=reverse(mid);
        boolean found=true;
        while(found && head!=null && revHead!=null){
            found&=(head.data==revHead.data);
            head=head.next;
            revHead=revHead.next;
        }
       return found; 
    }

}
