package algo.stack;

import java.io.*;
import java.util.*;

public class MaximumElementInStack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Stack<Integer> st = new Stack<Integer>();
		Stack<Integer> max = new Stack<Integer>();
		while (n-- > 0) {

			int q = sc.nextInt();
			switch (q) {
			case 1:
				int x = sc.nextInt();
				st.push(x);
				int top = max.isEmpty() ? x : Math.max(x, max.peek());
				max.push(top);
				break;
			case 2:
				st.pop();
				max.pop();
				break;
			case 3:
				System.out.println(max.peek());
				break;
			default:
				break;
			}

		}
	}
}