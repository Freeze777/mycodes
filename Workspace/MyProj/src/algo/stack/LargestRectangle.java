package algo.stack;

import java.util.Scanner;
import java.util.Stack;

public class LargestRectangle {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		int maxArea = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++)
			a[i] = sc.nextInt();

		Stack<Integer> st = new Stack<Integer>();
		int i = 0;
		while (i < n) {
			
				while (!st.isEmpty() && a[st.peek()] > a[i]) {

					int rightLimit = i;
					int curr = st.pop();
					int leftLimit = st.isEmpty() ? -1 : st.peek();

					int currArea = (rightLimit - leftLimit - 1) * a[curr];
					maxArea = Math.max(maxArea, currArea);

				}
				st.push(i++);
			

		}
		int rightLimit=-1;
		
		if (!st.isEmpty())
			rightLimit = st.peek();
		
		while (!st.isEmpty()) {

			int curr = st.pop();

			int leftLimit = st.isEmpty() ? -1 : st.peek();

			int currArea = (rightLimit - leftLimit) * a[curr];

			maxArea = Math.max(maxArea, currArea);

		}
		System.out.println(maxArea);
	}
}
