package algo.list;

public class DLLNode {
	public int data;
	public DLLNode next;
	public DLLNode previous;
	public DLLNode(int data) {
		this.data=data;
		this.next=null;
		this.previous=null;
	}
}
