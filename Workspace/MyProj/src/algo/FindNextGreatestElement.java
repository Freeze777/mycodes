package algo;

import java.util.Arrays;
import java.util.Stack;

public class FindNextGreatestElement {
	public static void main(String[] args) {
		int A[] = { 7, 5, 6, 9, 2, 3, 10, 22, 11, 10 };
		System.out.println(Arrays.toString(A));
		printNextGreatest(A);
		System.out.println(Arrays.toString(A));
	}

	public static void printNextGreatest(int[] A) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty())
				stack.push(i);
			else if (A[stack.peek()] >= A[i])
				stack.push(i);
			else {
				
				while ((!stack.isEmpty()) && (A[stack.peek()] < A[i]))
					A[stack.pop()]=A[i];
				
				stack.push(i);
			}
		}
		while (!stack.isEmpty())
			A[stack.pop()]=-1;
	}

}
