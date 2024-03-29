package algo.list;

import java.util.HashMap;

public class CopyListWithRandomPointer {
	public static void main(String[] args) {
		LLRNode a = new LLRNode(6);
		LLRNode b = a.next = new LLRNode(1);
		LLRNode c = b.next = new LLRNode(10);
		LLRNode d = c.next = new LLRNode(7);
		a.random = c;
		b.random = a;
		c.random = c;
		d.random = c;
		/*
		 * LLRNode head=cloneList_method1( a); LLRNode.printList(head);
		 */
		LLRNode head = cloneList_method2(a);
		LLRNode.printList(head);
	}

	private static LLRNode cloneList_method2(LLRNode head) {
		HashMap<LLRNode, LLRNode> map = new HashMap<LLRNode, LLRNode>();
		LLRNode thead = head;
		LLRNode newHead = new LLRNode(thead.data);
		LLRNode temp = newHead;
		thead = thead.next;
		map.put(head, newHead);
		while (thead != null) {

			temp.next = new LLRNode(thead.data);
			temp = temp.next;
			map.put(thead, temp);
			thead = thead.next;

		}
		temp = newHead;
		thead = head;
		while (temp != null) {

			temp.random = map.get(thead.random);
			temp = temp.next;
			thead = thead.next;
		}
		return newHead;
	}

	public static LLRNode cloneList_method1(LLRNode head) {
		LLRNode thead = head;

		while (thead != null) {
			LLRNode temp = new LLRNode(thead.data);
			temp.next = thead.next;
			thead.next = temp;
			thead = thead.next.next;

		}
		thead = head;
		while (thead != null) {
			thead.next.random = thead.random.next;
			thead = thead.next.next;

		}

		thead = head.next;
		LLRNode newHead = thead;

		while (head != null) {
			head.next = head.next.next;
			head = head.next;
			if (thead.next != null) {
				thead.next = thead.next.next;
				thead = thead.next;
			}

		}
		return newHead;
	}
}
