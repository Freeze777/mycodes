package algo.list;

import java.util.*;

public class AddTwoNumbersInLinkedList {
	public static ListNode reverse(ListNode head) {

		ListNode curr = head, prev = null, next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;

	}

	public static ListNode addLists_method1(ListNode num1, ListNode num2) {
		
		Stack<Integer> s1 = new Stack<Integer>();
		Stack<Integer> s2 = new Stack<Integer>();
		while (num1 != null) {
			s1.push(num1.data);
			num1 = num1.next;
		}
		while (num2 != null) {
			s2.push(num2.data);
			num2 = num2.next;
		}

		int carry = 0;
		ListNode res = null, head = null;

		while (!(s1.isEmpty()) || !(s2.isEmpty())) {
			int n1 = 0, n2 = 0;
			if (!s1.isEmpty())
				n1 = s1.pop();
			if (!s2.isEmpty())
				n2 = s2.pop();

			int sum = (n1 + n2 + carry);
			if (sum >= 10) {
				sum = sum - 10;
				carry = 1;
			} else {
				carry = 0;
			}
			if (head == null) {
				res = new ListNode(sum);
				head = res;
			} else {
				res.next = new ListNode(sum);
				res = res.next;
			}

		}
		if (carry != 0)
			res.next = new ListNode(carry);

		return reverse(head);
	}

	public static ListNode addLists_method2(ListNode num1, ListNode num2) {
		ListNode r1 = reverse(num1);
		ListNode r2 = reverse(num2);
		int carry = 0;
		int sum = 0;
		ListNode res = null, head = null;
		while (r1 != null || r2 != null) {
			int n1 = 0, n2 = 0;
			if (r1 != null) {
				n1 = r1.data;
				r1 = r1.next;
			}
			if (r2 != null) {
				n2 = r2.data;
				r2 = r2.next;
			}
			sum = n1 + n2 + carry;
			if (sum >= 10) {
				sum = sum - 10;
				carry = 1;
			} else {
				carry = 0;
			}
			if (head == null) {
				res = new ListNode(sum);
				// couldnt get the need for a head..!
				head = res;
			} else {
				{
					res.next = new ListNode(sum);
					res = res.next;
				}

			}
		}
		// Couldnt get this corner case
		if (carry != 0)
			res.next = new ListNode(carry);
		return reverse(head);
	}

	public static void main(String[] args) {
		ListNode num1 = new ListNode(9);
		num1.next = new ListNode(9);
		num1.next.next = new ListNode(9);
		num1.next.next.next = new ListNode(9);

		ListNode num2 = new ListNode(9);
		num2.next = new ListNode(9);
		num2.next.next = new ListNode(9);
		num2.next.next.next = new ListNode(9);
		num2.next.next.next.next = new ListNode(9);

		ListUtility.printList(addLists_method2(num1, num2));
	}

}
