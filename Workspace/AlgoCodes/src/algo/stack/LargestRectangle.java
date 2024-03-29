package algo.stack;

import java.util.Scanner;
import java.util.Stack;

import algo.Utils;

/*
 8
 2 4 3 5 2 4 1 5
 * */
public class LargestRectangle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * int n = sc.nextInt(); int[] a = new int[n]; for (int i = 0; i <
		 * a.length; i++) a[i] = sc.nextInt();
		 */
		int n = 8;
		int[] a = { 2, 4, 3, 5, 2, 4, 1, 5 };

		System.out.println(findLargestRectangle_method2(a));
		System.out.println(findLargestRectangle_method1(a));

	}

	private static int findLargestRectangle_method2(int[] a) {
		int[] nextSmallestRight = new int[a.length];
		int[] nextSmallestLeft = new int[a.length];
		Stack<Integer> s = new Stack<Integer>();
		for (int i = a.length - 1; i >= 0; i--) {
			if (s.isEmpty()) {
				nextSmallestRight[i] = -1;
			} else {
				// perfect..!
				if (a[i] > a[s.peek()]) {
					nextSmallestRight[i] = s.peek();
				} else {
					// popping out all elements greater than current element
					while (!s.isEmpty() && a[i] <= a[s.peek()])
						s.pop();
					nextSmallestRight[i] = s.isEmpty() ? -1 : s.peek();
				}
			}
			// we always have to push the current index.. but before that we
			// should pop out all elements greater than this from top of stack
			// why??we may not know whether this element is the next smallest
			// for the next number
			s.push(i);
		}
		s.clear();
		for (int i = 0; i < a.length; i++) {
			if (s.isEmpty()) {
				nextSmallestLeft[i] = -1;
			} else {
				if (a[i] > a[s.peek()]) {
					nextSmallestLeft[i] = s.peek();
				} else {
					while (!s.isEmpty() && a[i] <= a[s.peek()])
						s.pop();
					nextSmallestLeft[i] = s.isEmpty() ? -1 : s.peek();
				}
			}
			s.push(i);
		}

		int maxArea = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			int rightLimit = nextSmallestRight[i];
			int lefttLimit = nextSmallestLeft[i];
			maxArea = Math.max((rightLimit - lefttLimit - 1) * a[i], maxArea);
		}
		return maxArea;
	}

	private static int findLargestRectangle_method1(int[] a) {
		int maxArea = Integer.MIN_VALUE;
		Stack<Integer> st = new Stack<Integer>();
		int i = 0;
		while (i < a.length) {

			while (!st.isEmpty() && a[st.peek()] > a[i]) {

				int rightLimit = i;
				int curr = st.pop();
				int leftLimit = st.isEmpty() ? -1 : st.peek();

				int currArea = (rightLimit - leftLimit - 1) * a[curr];
				maxArea = Math.max(maxArea, currArea);

			}
			// we always push the current element... but before that we pop out
			// all elements till we get an increasing sequence on the stack
			st.push(i++);

		}
		int rightLimit = -1;

		if (!st.isEmpty())
			rightLimit = st.peek();

		while (!st.isEmpty()) {

			int curr = st.pop();

			int leftLimit = st.isEmpty() ? -1 : st.peek();

			int currArea = (rightLimit - leftLimit) * a[curr];

			maxArea = Math.max(maxArea, currArea);

		}
		return maxArea;
	}
}
