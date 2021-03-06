package algo.stack;

import java.util.Arrays;
import java.util.Stack;

public class FindNextGreatestElementOnRight {
	public static void main(String[] args) {
		int A[] = { 7, 5, 6, 9, 2, 3, 10, 22, 11, 10 };
		System.out.println(Arrays.toString(A));
		printNextGreatest_method2(A);
		printNextGreatest_method1(A);
		System.out.println(Arrays.toString(A));
	}

	private static void printNextGreatest_method2(int[] a) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = a.length - 1; i >= 0; i--) {
			if (s.empty())
				System.out.print(-1 + " ");
			else {
				if (a[i] < a[s.peek()])
					System.out.print(a[s.peek()] + " ");
				else {
					while (!s.isEmpty() && a[i] >= a[s.peek()])
						s.pop();
					System.out.print((s.isEmpty() ? -1 : a[s.peek()]) + " ");
				}
			}
			s.push(i);
		}
		System.out.println();
	}

	public static void printNextGreatest_method1(int[] A) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty() || A[stack.peek()] >= A[i])
				stack.push(i);
			else {
				while ((!stack.isEmpty()) && (A[stack.peek()] < A[i]))
					A[stack.pop()] = A[i];
				stack.push(i);
			}
		}
		while (!stack.isEmpty())
			A[stack.pop()] = -1;
	}

}
