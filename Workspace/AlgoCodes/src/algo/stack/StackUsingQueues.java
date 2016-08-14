package algo.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueues {
	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();

	public void push(int x) {
		q1.add(x);
	}

	// Removes the element on top of the stack.
	public void pop() {
		//flush out q1 to q2
		while (q1.size() > 1)
			q2.add(q1.remove());
		q1.remove();
		//swap q1 and q2 to prevent one more flush
		Queue<Integer> t = q1;
		q1 = q2;
		q2 = t;
	}

	// Get the top element.
	public int top() {
		//flush out q1 to q2
		while (q1.size() > 1)
			q2.add(q1.remove());
		int ans = q1.remove();
		q2.add(ans);
		//swap q1 and q2 to prevent one more flush
		Queue<Integer> t = q1;
		q1 = q2;
		q2 = t;
		return ans;
	}

	// Return whether the stack is empty.
	public boolean empty() {
		return q1.isEmpty() && q2.isEmpty();

	}
}
