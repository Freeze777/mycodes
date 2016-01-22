package algo.list;

public class LLRNode {
	public int data;
	public LLRNode next;
	public LLRNode random;
	public LLRNode(int data) {
		this.data=data;
		this.next=null;
		this.random=null;
	}
	
public static void printList(LLRNode head) {
	while (head != null) {
		System.out.print("["+head.data+","+head.random.data+"]" + " -> ");
		//System.out.print(head.data+ " -> ");
		head = head.next;
	}
	System.out.print(null + "\n");
}
}
