package algo.list;

public class RemoveNthFromLast {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null||head.next==null)//coudnt think of list size one.(given n will be valid)
            return null;
        ListNode crawl=head;
        while(crawl!=null && n>0){//crawl points to nth node from begining
            crawl=crawl.next;
            n--;
        }
        ListNode t=head;
        if(crawl!=null){//bug:couldnt handle this condition when crawl=null
            while(crawl.next!=null){
                t=t.next;
                crawl=crawl.next;
            }
            // t points to (n+1)th element from the end
            t.next=t.next.next;
        }else{
            head=head.next;// when we need to remove head... we dun have prev to it
        }
    
    return head;
        
    }
}