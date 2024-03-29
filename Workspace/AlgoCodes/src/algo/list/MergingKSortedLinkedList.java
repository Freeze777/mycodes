package algo.list;

import java.util.Comparator;
import java.util.PriorityQueue;

class NodeComparator implements Comparator<ListNode> {
	@Override
	public int compare(ListNode n1, ListNode n2) {

		return n1.data - n2.data;
	}
}

public class MergingKSortedLinkedList {
	public static ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(
				new NodeComparator());
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null)
				minHeap.add(lists[i]);
		}
		ListNode head=null;
		if(!minHeap.isEmpty()){
    		head = minHeap.remove();
    		if (head.next != null)
    			minHeap.add(head.next);
    		ListNode it = head;
    		while (!minHeap.isEmpty()) {
    			ListNode node = minHeap.remove();
    			it.next = node;
    			if (node.next != null)
    				minHeap.add(node.next);
    			it = it.next;
    		}
		    
		}

		return head;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(4);
		l1.next.next.next = new ListNode(9);

		ListNode l2 = new ListNode(0);
		l2.next = new ListNode(5);
		l2.next.next = new ListNode(7);
		l2.next.next.next = new ListNode(8);
		l2.next.next.next.next = new ListNode(15);

		ListNode l3 = new ListNode(10);
		l3.next = new ListNode(20);
		l3.next.next = new ListNode(41);
		l3.next.next.next = new ListNode(98);

		ListNode l4 = new ListNode(0);
		l4.next = new ListNode(15);
		l4.next.next = new ListNode(17);
		l4.next.next.next = new ListNode(48);
		l4.next.next.next.next = new ListNode(50);

		ListNode[] lists = { l1, l2, l3, l4 };
		
		ListUtility.printList(mergeKLists(lists));

	}
}